

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Advance {
    def apply[A](i: Long): Peg[A] = new AdvancePeg[A](i)
}

class AdvancePeg[A](i: Long) extends Peg[A] {
    Assert(i >= 0)

    override def parse(v: Vector[A], first: Long, last: Long) = {
        val cur = first + i
        if (cur <= last) {
            cur
        } else {
            Peg.FAILURE
        }
    }

    override def length = i
}
