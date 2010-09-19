

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.actors.Actor
import scala.actors.IScheduler


class Reactor(sc: IScheduler = null) extends Actor { self =>
    private var func: Any => Unit = null

    override def act = {
        Actor.loop {
            react {
                case e => func(e)
            }
        }
    }

    lazy val reactive: Reactive[Any] = new ReactiveOnce[Any] {
        override protected def foreachOnce(f: Any => Unit) = {
            self.func = f
            self.start
        }
    }

    override def scheduler = if (sc == null) super.scheduler else sc
}
