

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Range[A](_1: A, _2: A, _3: Compare[A]) extends Peg[A] {
    private val ord = _3.toOrdering

    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (start == end) {
            FAILURE
        } else {
            val e = v(start)
            if (ord.lteq(_1, e) && ord.lteq(e, _2)) {
                start + 1
            } else {
                FAILURE
            }
        }
    }

    override def width = 1
}
