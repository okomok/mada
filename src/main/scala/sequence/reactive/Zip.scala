

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.LinkedList


private
case class Zip[+A, +B](_1: Reactive[A], _2: Reactive[B]) extends Forwarder[(A, B)] {
    override protected val delegate = _1.zipBy(_2){ (a, b) => (a, b) }
}

// Lock-free please!
private
case class ZipBy[A, B, +C](_1: Reactive[A], _2: Reactive[B], _3: (A, B) => C) extends Reactive[C] {
    override def close = { _1.close; _2.close }
    override def foreach(f: C => Unit) = {
        val q1 = new LinkedList[A]
        val q2 = new LinkedList[B]
        val lock = new AnyRef
        def invariant() = assert(q1.isEmpty || q2.isEmpty)

        for (x <- _1) {
            lock.synchronized {
                invariant
                if (q2.isEmpty) {
                    q1.add(x)
                } else {
                    f(_3(x, q2.poll))
                }
            }
        }

        for (y <- _2) {
            lock.synchronized {
                invariant()
                if (q1.isEmpty) {
                    q2.add(y)
                } else {
                    f(_3(q1.poll, y))
                }
            }
        }
    }
}
