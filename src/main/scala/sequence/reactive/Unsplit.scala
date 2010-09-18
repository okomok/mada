

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Unsplit[A](_1: Reactive[Sequence[A]], _2: Reactive[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        @volatile var first = true
        for (s <- _1) {
            if (first) {
                first = false
            } else {
                for (x <- _2) f(x)
            }
            for (x <- s.asReactive) f(x)
        }
    }
}
