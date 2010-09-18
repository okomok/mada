

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Init[+A](_1: Reactive[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        @volatile var prev: Option[A] = None
        for (x <- _1) {
            if (!prev.isEmpty) {
                f(prev.get)
            }
            prev = Some(x)
        }
    }
}
