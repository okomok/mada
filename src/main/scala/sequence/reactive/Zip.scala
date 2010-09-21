

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.LinkedList


// Lock-free please!
private
case class Zip[A, B](_1: Reactive[A], _2: Reactive[B]) extends Reactive[Tuple2[A, B]] {
    override def foreach(f: Tuple2[A, B] => Unit) = {
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
                    f(x, q2.poll)
                }
            }
        }

        for (y <- _2) {
            lock.synchronized {
                invariant()
                if (q1.isEmpty) {
                    q2.add(y)
                } else {
                    f(q1.poll, y)
                }
            }
        }
    }
}
