

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

    private var out: Any => Unit = null

    final override def act = {
        Actor.loop {
            react {
                case e => out(e)
            }
        }
    }

    final override def start = {
        startReactive(new Reactive[Any] {
            override def close = Actor.exit
            override def foreach(f: Any => Unit) = { self.out = f }
        })
        super.start
    }

    final override def restart = {
        startReactive(new Reactive[Any] {
            override def close = Actor.exit
            override def foreach(f: Any => Unit) = { self.out = f }
        })
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


/*
class Reactor extends Reactive[Any] { self =>
    private var out: Any => Unit = null

    private def startActor = actor.getState match {
        case Actor.State.Terminated => actor.restart
        case _ => actor.start
    }

    val actor = new Actor {
        override def act = {
            Actor.loop {
                react {
                    case e => self.out(e)
                }
            }
        }
    }

    override def close = Actor.exit
    override def foreach(f: Any => Unit) = {
        out = f
        startActor
    }
}
*/
