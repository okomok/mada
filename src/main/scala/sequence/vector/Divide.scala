

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private[mada] case class Divide[A](val _1: Vector[A], _2: Int) extends Vector[Vector[A]] {
    Precondition.positive(_2, "stride")

    override def start = 0
    override def end = _Step.count(_1.start, _1.end, _2)
    override def apply(i: Int) = {
        val cur = _1.start + i * _2
        new Region(_1, cur, java.lang.Math.min(cur + _2, _1.end))
    }
    // isDefinedAt is restrictive because _1.end affects.

    override def undivide[B](implicit pre: Vector[Vector[A]] <:< Vector[Vector[B]]): Vector[B] = _1.asInstanceOf[Vector[B]] // undivide-divide fusion
}


private[mada] case class Undivide[A](_1: Vector[Vector[A]]) extends Forwarder[A] {
    override protected val delegate: Vector[A] = {
        if (_1.isEmpty) {
            empty[A]
        } else {
            new _Undivide(_1)
        }
    }
}

private class _Undivide[A](_1: Vector[Vector[A]]) extends Vector[A] {
    assert(!_1.isEmpty)

    override def start = 0
    override def end = (quotient * divisor) + remainder

    override def apply(i: Int) = {
        val d = divisor
        _1.nth(Div.quotient(i, d)).nth(Div.remainder(i, d))
    }
    override def update(i: Int, e: A) = {
        val d = divisor
        _1.nth(Div.quotient(i, d)).nth(Div.remainder(i, d)) = e
    }
    override def isDefinedAt(i: Int) = {
        val d = divisor
        _1.nth.isDefinedAt(Div.quotient(i, d)) &&
        _1.nth(Div.quotient(i, d)).nth.isDefinedAt(Div.remainder(i, d))
    }

    private def quotient: Int = _1.nth.size - 1
    private def divisor: Int = _1.nth.head.size
    private def remainder: Int = _1.nth.last.size
}
