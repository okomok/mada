

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Minus[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur1 = _1.parse(v, start, end)
        if (cur1 == FAILURE) {
            FAILURE
        } else {
            val cur2 = _2.parse(v, start, end)
            if (cur2 == FAILURE || cur2 < cur1) {
                cur1
            } else {
                FAILURE
            }
        }
    }

    override def width = _1.width
}
