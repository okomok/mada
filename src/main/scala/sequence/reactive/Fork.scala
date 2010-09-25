

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Fork[A](_1: Reactive[A], _2: Reactive[A] => Unit) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) = {
        var parent = _1
        val child = new Reactive[A] {
            override def foreach(g: A => Unit) = { parent = _1.doing(g) }
        }
        _2(child)
        parent.foreach(f)
    }
}
