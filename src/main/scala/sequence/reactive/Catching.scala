

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Catching[A](_1: Reactive[A], _2: Throwable => Unit) extends TransformAdapter[A] {
    override def underlying = _1
    override def foreach(f: A => Unit) = {
        for (x <- _1) {
            try {
                f(x)
            } catch {
                case t: Throwable => _2(t)
            }
        }
    }
}
