

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Future {
    val usingActors = false
    def apply[T](body: => T) = Futures.future(body)
}

object Futures {
    import java.util.concurrent._

    val corePoolSize = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // ScheduledExecutorService results in deadlock around nested futures.
    val exe = Executors.newCachedThreadPool() // newScheduledThreadPool(corePoolSize)

    def future[T](body: => T): (() => T) = {
        try {
            new Future(body)
        } catch {
            case e: RejectedExecutionException => new Present(body)
        }
    }

    class Future[T](body: => T) extends (() => T) {
        private val c = new Callable[T] { override def call(): T = body }
        private val u = exe.synchronized { exe.submit(c) }
        override def apply(): T = u.get
    }

    class Present[T](body: => T) extends (() => T) {
        private lazy val get = body
        override def apply(): T = get
    }
}

// See: http://www.nabble.com/Actors-and-futures-td14926230.html
//   Actors too doesn't support nested futures:
//   val f = future { Thread.sleep(1000); "Scala" }
//   val f2 = future { f().length * 2 }
//   --> assertion: receive from channel belonging to other actor
//       or `lazy val` may incur deadlock.
object ActorsFutures {
    def future[T](body: => T) = scala.actors.Futures.future(body)
}
