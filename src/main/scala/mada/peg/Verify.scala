

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


/**
 * Thrown in case <code>verify</code> doesn't match.
 */
case class VerificationException[A](peg: Peg[A], vector: sequence.Vector[A]) extends RuntimeException


case class Verify[A](_1: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val cur = _1.parse(v, start, end)
        if (cur == FAILURE) {
            throw new VerificationException(_1, v(start, end))
        }
        cur
    }

    override def width = _1.width
}
