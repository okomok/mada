

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Fork[A](_1: Reactive[A], _2: Reactive[A] => Unit) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var one = _1
        val two = new Reactive[A] {
            override def foreach(g: A => Unit) = { one = _1.doing(g) }
        }
        _2(two)
        one.foreach(f)
    }
}
