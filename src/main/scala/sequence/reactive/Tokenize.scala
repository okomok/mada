

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Tokenize[A](_1: Reactive[A], _2: Peg[A]) extends Reactive[Vector[A]] {
    override def close() = _1.close()
    override def forloop(f: Vector[A] => Unit, k: Exit => Unit) {
        val buf = new java.util.ArrayList[A]
        _1 _for { x =>
            buf.add(x)
            val ts = _2.tokenize(vector.from(buf).readOnly)
            var last = 0
            for (t @ vector.Region(_, _, j) <- ts) {
                f(t)
                last = j
            }
            buf.subList(0, last).clear()
        } _then {
            k
        }
    }
}
