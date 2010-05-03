

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


case class Opt[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    override protected val delegate = _1.repeat(0, 1)
}

case class Plus[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    Precondition.zeroWidth(_1, "plus")
    override protected val delegate = _1.repeat(1, ())
}

case class Star[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    Precondition.zeroWidth(_1, "star")
    override protected val delegate = _1.repeat(0, ())
}


case class Repeat[A](_1: Peg[A], _2: Int, _3: Int) extends Forwarder[A] with Quantified[A] {
    if (_2 < 0 || _2 > _3) {
        throw new IllegalArgumentException("repeat" + (_2, _3))
    }

    private val prefix = new RepeatExactly(_1, _2)
    override protected val delegate = prefix >> new RepeatAtMost(_1, _3 - _2)
    override def until(that: Peg[A]) = prefix >> new RepeatAtMostUntil(_1, _3 - _2, that)
}


private[mada] class RepeatExactly[A](_1: Peg[A], _2: Int) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int): Int = {
        var cur = start
        var i = 0
        while (i != _2) {
            cur = _1.parse(v, cur, end)
            if (cur == FAILURE) {
                return FAILURE
            }
            i += 1
        }
        cur
    }

    override def width = _1.width * _2
}


private[mada] class RepeatAtMost[A](_1: Peg[A], _2: Int) extends Peg[A] {
    // RepeatAtMostUntil(_1, _2, !_1) would include redundant parsing.
    override def parse(v: sequence.Vector[A], start: Int, end: Int): Int = {
        var cur = start
        var i = 0
        while (i != _2 && cur != end) {
            val next = _1.parse(v, cur, end)
            if (next == FAILURE) {
                return cur
            }
            cur = next
            i += 1
        }
        cur
    }
}


private[mada] class RepeatAtMostUntil[A](_1: Peg[A], _2: Int, _3: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = parseImpl(v, start, end)._3

    def parseImpl(v: sequence.Vector[A], start: Int, end: Int): (Int, Int, Int) = {
        var cur = start
        var i = 0
        var next = _3.parse(v, cur, end)
        while (i != _2 && next == FAILURE) {
            next = _1.parse(v, cur, end)
            if (next == FAILURE) {
                return (start, cur, FAILURE)
            }
            cur = next
            i += 1
            next = _3.parse(v, cur, end)
        }
        (start, cur, next)
    }
}
