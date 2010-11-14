

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.actors.Actor
import java.util.concurrent.CopyOnWriteArrayList


/**
 * The Reactive Actor
 */
trait Reactor extends Actor {
    /**
     * Override to build up a Reactive.
     */
    protected def startReactive(r: Reactive[Any]): Unit

    private var _f: Any => Unit = function.empty1 // primary
    private val _gs = new CopyOnWriteArrayList[Any => Unit] // secondaries

    final override def act = {
        Actor.loop {
            react {
                case Reactor.Exit => Actor.exit
                case x => {
                    _f(x)
                    iterative.from(_gs).foreach{g => g(x)}
                }
            }
        }
    }

    final override def start = {
        startReactive(Reactor.Primary(this))
        super.start
    }

    final override def restart = {
        startReactive(Reactor.Primary(this))
        super.restart
    }
}


object Reactor {

    /**
     * Message to exit a Reactor.
     */
    object Exit

    /**
     * Message to generate something.
     */
    object Generate

    /**
     * Constructs a trivial Reactor.
     */
    def apply(f: Reactive[Any] => Unit = Starter): Reactor = {
        val a = new Reactor {
            override protected def startReactive(r: Reactive[Any]) = f(r)
        }
        a.start
        a
    }

    /**
     * Constructs a single-threaded Reactor.
     */
    def singleThreaded(f: Reactive[Any] => Unit = Starter): Reactor = {
        val a = new Reactor {
            override protected def startReactive(r: Reactive[Any]) = f(r)
            override def scheduler = new scala.actors.scheduler.SingleThreadedScheduler
        }
        a.start
        a
    }

    private
    object Starter extends (Reactive[Any] => Unit) {
        override def apply(r: Reactive[Any]) = r.start
    }

    private
    final class Wrap(f: Any => Unit) extends (Any => Unit) {
        override def apply(x: Any) = f(x)
    }

    private
    case class Primary(_1: Reactor) extends Reactive[Any] {
        override def foreach(f: Any => Unit) {
            _1._f = f
        }
    }

    private[reactive]
    case class Secondary(_1: Reactor) extends Resource[Any] {
        private[this] var g: Any => Unit = null
        override protected def closeResource() = _1._gs.remove(g)
        override protected def openResource(f: Any => Unit) {
            g = new Wrap(f)
            _1._gs.add(g)
        }
    }

}
