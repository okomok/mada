

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Adjacent[A](_1: Reactive[A], _2: Int) extends Reactive[Vector[A]] {
    override def close() = _1.close()
    override def foreach(f: Vector[A] => Unit, k: => Unit) {
        val buf = new AdjacentBuffer[A](_2)
        _1 _for { x =>
            buf.addLast(x)
            if (buf.isFull) {
                f(buf.toVector)
                buf.removeFirst()
            }
        } _then {
            // FIXME: flush buffer
            k
        }

    }
}
