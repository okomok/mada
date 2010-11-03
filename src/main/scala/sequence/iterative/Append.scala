

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Append[+A](_1: Iterative[A], _2: Iterative[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] var it = _1.begin
        private[this] var inLeft = true
        ready

        override def isEnd = !it
        override def deref = ~it
        override def increment {
            it.++
            ready
        }

        private def ready {
            if (!it && inLeft) {
                it = _2.begin
                inLeft = false
            }
        }
    }
}
