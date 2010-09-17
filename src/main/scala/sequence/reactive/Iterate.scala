

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Iterate[A](_1: A, _2: A => A) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var acc = _1
        while (true) {
            f(acc)
            acc = _2(acc)
        }
    }
}
