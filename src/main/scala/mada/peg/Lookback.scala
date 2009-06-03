

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Lookback[A](_1: Peg[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val x = v.reverse
        if (_1.parse(x, x.end - (start - v.start), x.end) == FAILURE) {
            FAILURE
        } else {
            start
        }
    }
}
