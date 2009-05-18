

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Thrown in case <code>verify</code> doesn't match.
 */
case class VerificationException[A](peg: Peg[A], vector: Vector[A]) extends RuntimeException


private[mada] object Verify {
    def apply[A](p: Peg[A]): Peg[A] = new VerifyPeg(p)
}

private[mada] class VerifyPeg[A](override val delegate: Peg[A]) extends Forwarder[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = delegate.parse(v, start, end)
        if (cur == FAILURE) {
            throw new VerificationException(delegate, v(start, end))
        }
        cur
    }
}
