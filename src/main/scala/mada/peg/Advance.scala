

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Advance {
    def apply[A](n: Int): Peg[A] = new AdvancePeg[A](n)
}

private[mada] class AdvancePeg[A](n: Int) extends Peg[A] {
    Assert(n >= 0)

    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = start + n
        if (cur <= end) {
            cur
        } else {
            FAILURE
        }
    }

    override def width = n
}
