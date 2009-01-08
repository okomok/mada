

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Advance {
    def apply[A](i: Long): Peg[A] = new AdvancePeg[A](i)
}

class AdvancePeg[A](i: Long) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val cur = first + i
        if (0 <= cur && cur <= last) {
            cur
        } else {
            FAILURE
        }
    }

    override def length = {
        if (i < 0) {
            throw new UnsupportedOperationException("AdvancePeg.length is negative")
        } else {
            i
        }
    }
}
