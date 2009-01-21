

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Advance {
    def apply[A](i: Int): Peg[A] = new AdvancePeg[A](i)
}

class AdvancePeg[A](i: Int) extends Peg[A] {
    Assert(i >= 0)

    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = start + i
        if (cur <= end) {
            cur
        } else {
            Peg.FAILURE
        }
    }

    override def length = i
}
