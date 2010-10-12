

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class And[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val cur1 = _1.parse(v, start, end)
        if (cur1 != FAILURE) {
            val cur2 = _2.parse(v, start, end) // short-circuit
            if (cur1 == cur2) {
                cur1
            } else {
                FAILURE
            }
        } else {
            FAILURE
        }
    }

    override def width = _1.width
}
