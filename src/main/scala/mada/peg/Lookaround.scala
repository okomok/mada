

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Lookaround3[A](_1: vector.Pred3[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (_1(v, start, end)) {
            start
        } else {
            FAILURE
        }
    }
}
