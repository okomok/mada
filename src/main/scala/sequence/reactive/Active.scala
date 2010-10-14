

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.actors.Actor


private
case class Active[+A](_1: Reactive[A]) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        val a = new Actor {
            override def act = {
                Actor.loop {
                    react {
                        case x => f(x.asInstanceOf[A])
                    }
                }
            }
        }
        a.start
        for (x <- _1) {
            a ! x
        }
    }
}
