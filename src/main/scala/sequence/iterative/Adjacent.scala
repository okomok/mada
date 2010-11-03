

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Adjacent[A](_1: Iterative[A], _2: Int) extends Iterative[Vector[A]] {
    override def begin = {
        new Iterator[Vector[A]] {
            private[this] val it = _1.begin
            private[this] val buf = new AdjacentBuffer[A](_2)
            while (it && !buf.isFull) {
                buf.addLast(~it)
                it.++
            }
            override def isEnd = !buf.isFull
            override def deref = buf.toVector
            override def increment {
                buf.removeFirst
                if (it) {
                    buf.addLast(~it)
                    it.++
                }
            }
        }
    }
}
