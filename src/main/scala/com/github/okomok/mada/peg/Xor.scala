

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


case class Xor[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val cur1 = _1.parse(v, start, end)
        val cur2 = _2.parse(v, start, end)

        val ok1 = cur1 != FAILURE
        if (ok1 && cur2 != FAILURE) {
            FAILURE
        } else if (ok1) {
            cur1
        } else {
            cur2
        }
    }
}
