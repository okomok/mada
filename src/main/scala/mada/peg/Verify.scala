

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Verify_ {
    def apply[A](p: Peg[A]): Peg[A] = new VerifyPeg(p)
}

class VerifyPeg[A](override val self: Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        val cur = self.parse(v, first, last)
        if (cur == Peg.FAILURE) {
            throw new VerificationException(self, (v, first, last))
        }
        cur
    }
}

case class VerificationException[A](peg: Peg[A], vector: Vector.Triple[A]) extends RuntimeException
