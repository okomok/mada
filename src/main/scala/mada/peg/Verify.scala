

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Thrown in case <code>Peg.verify</code> doesn't match.
 */
case class VerificationException[A](peg: Peg[A], vector: Vector.Triple[A]) extends RuntimeException


private[mada] object Verify {
    def apply[A](p: Peg[A]): Peg[A] = new VerifyPeg(p)
}

private[mada] class VerifyPeg[A](override val self: Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = self.parse(v, start, end)
        if (cur == Peg.FAILURE) {
            throw new VerificationException(self, (v, start, end))
        }
        cur
    }
}
