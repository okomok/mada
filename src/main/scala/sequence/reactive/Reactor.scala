

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.actors.Actor
import scala.actors.IScheduler


class Reactor[A](sc: IScheduler = null) extends Actor { self =>
    private val func = new VarOnce[A => Unit]

    override def act = {
        Actor.loop {
            react {
                case e => func(e.asInstanceOf[A])
            }
        }
    }

    lazy val reactive: Reactive[A] = new Reactive[A] {
        override def foreach(f: A => Unit) = {
            self.func := f
            self.start
        }
    }

    override def scheduler = if (sc == null) super.scheduler else sc
}


object Reactor {
    implicit def _toReactive[A](from: Reactor[A]): Reactive[A] = from.reactive
}
