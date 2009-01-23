

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


// See: http://www.nabble.com/Actors-Break-Futures-td13813999.html
//      http://www.nabble.com/Proxies%3A-Smalltalk-is-to--doesNotUnderstand%3A-as-Scala-is-to-----td15147719.html
// A nested parallels doesn't work:
//    parallel.flatMap{ case i => ...parallel.map(f) }
// Also note lazy val results in deadlock.

object Future {
    def apply[T](body: => T) = JavaFutures.future(body) // ActorsFutures.future(body)
}

object ActorsFutures {
    def future[T](body: => T) = scala.actors.Futures.future(body)
}

object JavaFutures {
    import java.util.concurrent._

    private val exe = new ScheduledThreadPoolExecutor(20)

    def future[T](body: => T) = this.synchronized {
        new {
            private val c = new Callable[T] { override def call: T = body }
            private val u = exe.submit(c)
            def apply: T = u.get
        }
    }
}

/*
class Slave extends scala.actors.Actor {
    import scala.actors._

    def act {
        loop {
            react {
                case Work(arg, work) => {
                    val future = new CruftFuture[Any]
                    reply(future)
                    future.setValue(work(arg))
                }
                case Exit() => exit
            }
        }
    }

    def !!![A, B](work: Work[A, B]) : Future[Any] = {
        (this !? work).asInstanceOf[CruftFuture[Any]]
    }

    private class CruftFuture[A](ch: InputChannel[Any]) extends Future[A](ch) {
        var cruftValue : Option[A] = None

        def this() = {
            this(null)
        }

        def isSet = cruftValue match {
            case None => false
            case Some(any) => true
        }

        def apply = {
            while(!isSet) {}
            cruftValue.get
        }

        def setValue(value : A) = {
            cruftValue = Some(value)
        }
    }
}
*/
