

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


private[mada] object Future {
    import java.util.concurrent._

    val corePoolSize = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // Note that scala.actors and ScheduledExecutorService don't work with nested futures.
    private val exe = Executors.newCachedThreadPool()

    def apply[R](body: => R): Function0[R] = {
        try {
            new ByParallel(body)
        } catch {
            case _: RejectedExecutionException => Functions.byLazy(body)
        }
    }

    private class ByParallel[R](body: => R) extends Function0[R] {
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
