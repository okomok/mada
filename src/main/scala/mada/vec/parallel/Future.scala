

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel



object Future {
    val usingActors = false
    def apply[T](body: => T) =
        JavaFutures.future(body)
        // ActorsFutures.future(body)
}

object JavaFutures {
    import java.util.concurrent._

    private val exe = Executors.newCachedThreadPool()

    def future[T](body: => T) = new {
        private val c = new Callable[T] { override def call(): T = body }
        private val u = exe.synchronized { exe.submit(c) }
        def apply(): T = u.get
    }
}

// Actors doesn't support nested futures:
//   val f = future { Thread.sleep(1000); "Scala" }
//   val f2 = future { f().length * 2 }
//   --> assertion: receive from channel belonging to other actor
//       or `lazy val` may result in deadlock.
object ActorsFutures {
    def future[T](body: => T) = scala.actors.Futures.future(body)
}
