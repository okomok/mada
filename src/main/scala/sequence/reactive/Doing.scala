

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Doing[A](_1: Reactive[A], _2: A => Unit) extends TransformAdapter[A] {
    override def underlying = _1
    override def foreach(f: A => Unit) = {
        for (x <- _1) {
            _2(x)
            f(x)
        }
    }
}
