

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private[mada] case class SeqAnd[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        var cur = _1.parse(v, start, end)
        if (cur == FAILURE) {
            FAILURE
        } else {
            _2.parse(v, cur, end)
        }
    }

    override def width = _1.width + _2.width
}

private[mada] case class SeqOr[A](_1: Peg[A], _2: Peg[A]) extends Forwarder[A] {
    override protected val delegate = (_1 >> _2.?) | _2
}

private[mada] case class SeqImply[A](_1: Peg[A], _2: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val cur = _1.parse(v, start, end)
        if (cur == FAILURE) {
            start
        } else {
            _2.parse(v, cur, end)
        }
    }
}

private[mada] case class SeqOpt[A](_1: Peg[A], _2: Peg[A]) extends Forwarder[A] {
    override protected val delegate = _2 >> _1.?
}
