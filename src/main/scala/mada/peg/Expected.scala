

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Expected {
    def apply[A](p: Peg[A]): Peg[A] = new ExpectedPeg(p)
}

class ExpectedPeg[A](override val self: Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        val cur = self.parse(v, first, last)
        if (cur == FAILURE) {
            throw new ExpectedException(self, (v, first, last))
        }
        cur
    }
}

case class ExpectedException[A](peg: Peg[A], vector: (Vector[A], Long, Long)) extends RuntimeException
