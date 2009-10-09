

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Lookbehind[A](_1: Peg[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val len = _1.width
        if (start - len < v.start) {
            FAILURE
        } else {
            if (start == _1.parse(v, start - len, start)) {
                start
            } else {
                FAILURE
            }
        }
    }
}
