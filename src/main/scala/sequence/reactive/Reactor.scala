

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
    private val outs = new CopyOnWriteArrayList[Any => Unit]

    final override def act = {
        Actor.loop {
            react {
                case Reactor.Exit => Actor.exit
                case x => iterative.from(outs).foreach{f => f(x)}
            }
        }
    }

    final def sequence: Reactive[Any] = Reactor.Sequence(this)
}


object Reactor {

    /**
     * Message to exit a Reactor.
     */
    object Exit

    /**
     * Constructs a trivial Reactor.
     */
    def apply(f: Reactive[Any] => Unit): Reactor = {
        val a = new Reactor{}
        f(a.sequence)
        a.start
        a
    }

    private
    final class OutWrap(f: Any => Unit) extends (Any => Unit) {
        override def apply(x: Any) = f(x)
    }

    private
    case class Sequence(_1: Reactor) extends Resource[Any] {
        private var out: Any => Unit = null
        override protected def closeResource = _1.outs.remove(out)
        override protected def openResource(f: Any => Unit) = {
            out = new Reactor.OutWrap(f)
            _1.outs.add(out)
        }
    }

}
