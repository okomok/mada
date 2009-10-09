

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Negate[A](_1: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (_1.parse(v, start, end) != FAILURE) {
            FAILURE
        } else {
            val cur = start + _1.width
            if (cur <= end) {
                cur
            } else {
                FAILURE
            }
        }
    }

    override def width = _1.width
}
