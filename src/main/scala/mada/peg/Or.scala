

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Or[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        var cur = _1.parse(v, start, end)
        if (cur == FAILURE) {
            _2.parse(v, start, end)
        } else {
            cur
        }
    }

    override def width = {
        val w1 = _1.width
        if (w1 != _2.width) {
            throw new NotConstantWidth("or")
        } else {
            w1
        }
    }
}
