

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object Future {
    def apply[T](body: => T) = Futures.future(body)
}

private[mada] object Futures {
    import java.util.concurrent._

    val corePoolSize = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // ScheduledExecutorService results in deadlock around nested futures.
    val exe = Executors.newCachedThreadPool() // newScheduledThreadPool(corePoolSize)

    def future[T](body: => T): (() => T) = {
        try {
            new Future(body)
        } catch {
            case e: RejectedExecutionException => Functions.byLazy(body)
        }
    }

    class Future[T](body: => T) extends (() => T) {
        private val c = new Callable[T] { override def call(): T = body }
        private val u = exe.synchronized { exe.submit(c) }
        override def apply(): T = u.get
    }
}

/*
// See: http://www.nabble.com/Actors-and-futures-td14926230.html
//   Actors don't support nested futures:
//   val f = future { Thread.sleep(1000); "Scala" }
//   val f2 = future { f().length * 2 }
//   --> assertion: receive from channel belonging to other actor
//       or `lazy val` may incur deadlock.
private[mada] object ActorsFutures {
    def future[T](body: => T) = scala.actors.Futures.future(body)
}
*/
