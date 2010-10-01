

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.actors.Actor
import scala.actors.IScheduler


/**
 * The Reactive Actor
 */
trait Reactor extends Actor { self =>
    /**
     * Override to build a Reactive.
     */
    protected def startReactive(r: Reactive[Any]): Unit

    private var g: Any => Unit = null

    private class _Impl extends ReactiveOnce[Any] {
        override def close = Actor.exit
        override protected def foreachOnce(f: Any => Unit) = { self.g = f }
    }

    final override def act = {
        Actor.loop {
            react {
                case e => g(e)
            }
        }
    }

    final override def start = {
        startReactive(new _Impl)
        super.start
    }

    final override def restart = {
        startReactive(new _Impl)
        super.restart
    }
}


object Reactor {
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
