

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


private[mada] object Future {
    import java.util.concurrent._
    import java.lang.Runnable

    val POOL_SIZE = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // Interdependent tasks need unbounded pools to avoid starvation deadlock.
    // (scala.actors.Future doesn't support dependent tasks.)
    private val exe =
        new ThreadPoolExecutor(0, POOL_SIZE, 60L, TimeUnit.SECONDS, new SynchronousQueue[Runnable]())
        // Executors.newCachedThreadPool()

    def apply[R](body: => R): Function0[R] = {
        try {
            new OfParallel(body)
        } catch {
            case _: RejectedExecutionException => function.ofLazy(body)
        }
    }

    private class OfParallel[R](body: => R) extends Function0[R] {
        private val c = new Callable[R] { override def call(): R = body }
        private val u = exe.synchronized { exe.submit(c) }
        override def apply(): R = {
            try {
                u.get
            } catch {
                case e: ExecutionException => throw e.getCause
            }
        }
    }
}
