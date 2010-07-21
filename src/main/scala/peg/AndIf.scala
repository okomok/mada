

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private[mada] case class AndIf[A](_1: Peg[A], _2: sequence.vector.Pred[A]) extends Forwarder[A] {
    override protected val delegate = _1.andIf3(sequence.vector.triplify(_2))
}

private[mada] case class AndIf3[A](_1: Peg[A], _2: sequence.vector.Pred3[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val cur = _1.parse(v, start, end)
        if (cur == FAILURE || !_2(v, start, cur)) {
            FAILURE
        } else {
            cur
        }
    }

    override def width = _1.width
}
