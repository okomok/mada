

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class TakeWhile[A](_1: Iterative[A], _2: A => Boolean) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private var it = _1.begin
        ready

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            it.++
            ready
        }

        private def ready: Unit = {
            if (it && !_2(~it)) {
                it = iterator.end
            }
        }
    }
}
