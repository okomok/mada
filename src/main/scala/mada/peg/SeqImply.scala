

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class SeqImply[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = _1.parse(v, start, end)
        if (cur == FAILURE) {
            start
        } else {
            _2.parse(v, cur, end)
        }
    }
}
