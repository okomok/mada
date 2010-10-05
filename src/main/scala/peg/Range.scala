

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private
case class Range[A](_1: A, _2: A, _3: Ordering[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (start == end) {
            FAILURE
        } else {
            val e = v(start)
            if (_3.lteq(_1, e) && _3.lteq(e, _2)) {
                start + 1
            } else {
                FAILURE
            }
        }
    }

    override def width = 1
}
