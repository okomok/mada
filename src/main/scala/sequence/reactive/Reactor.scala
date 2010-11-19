

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
    private var _k: eval.ByName[Unit] = eval.ByName(())
    private val _fs = new CopyOnWriteArrayList[Any => Unit] // secondaries
    private val _ks = new CopyOnWriteArrayList[eval.ByName[Unit]]

    final override def act = {
        Actor.loop {
            react {
                case Reactor.Exit => {
                    _k() // ?
                    iterative.from(_ks).foreach{k => k()}
                    Actor.exit
                }
                case x => {
                    _f(x)
                    iterative.from(_fs).foreach{f => f(x)}
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
        override def forloop(f: Any => Unit, k: => Unit) {
            _1._f = f
            _1._k = eval.ByName(k)
        }
    }

    private[reactive]
    case class Secondary(_1: Reactor) extends Resource[Any] {
        private[this] var _f: Any => Unit = null
        private[this] var _k: eval.ByName[Unit] = null
        override protected def closeResource() {
            _1._ks.remove(_k)
            _1._fs.remove(_f)
        }
        override protected def openResource(f: Any => Unit, k: => Unit) {
            _f = new Wrap(f)
            _k = eval.ByName(k)
            _1._fs.add(_f)
            _1._ks.add(_k)
        }
    }

}
