

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.actors.Actor
import scala.actors.IScheduler


/**
 * The Reactive Actor
 */
trait Reactor extends Actor {
    /**
     * Override to build a Reactive.
     */
    protected def startReactive(r: Reactive[Any]): Unit

    @volatile private var g: Any => Unit = null

    final override def act = {
        Actor.loop {
            react {
                case Reactor.Exit => Actor.exit
                case e => g(e)
            }
        }
    }

    final override def start: Actor = {
        super.start
        startReactive(new Reactor.Impl(this))
        this
    }

    final override def restart = {
        super.restart
        startReactive(new Reactor.Impl(this))
    }
}


object Reactor {
    private object Exit

    private class Impl(r: Reactor) extends Resource[Any] {
        override protected def openResource(f: Any => Unit) = { r.g = f }
        override protected def closeResource = r ! Exit
    }

    /**
     * Constructs a trivial Reactor.
     */
    def apply(f: Reactive[Any] => Unit): Actor = {
        val a = new Reactor {
            override def startReactive(r: Reactive[Any]) = f(r)
        }
        a.start
    }
}
