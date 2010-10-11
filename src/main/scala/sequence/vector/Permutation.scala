

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private
case class Permutation[A](_1: Vector[A], _2: Int => Int) extends TransformAdapter[A] {
    override protected val underlying = _1.nth

    override def apply(i: Int) = underlying(_2(i))
    override def update(i: Int, e: A) = underlying(_2(i)) = e
    override def isDefinedAt(i: Int) = underlying.isDefinedAt(_2(i))
}


private
case class Nth[A](_1: Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.size

    override def apply(i: Int) = _1(_1.start + i)
    override def update(i: Int, e: A) = _1(_1.start + i) = e
    override def isDefinedAt(i: Int) = _1.isDefinedAt(_1.start + i)

    override def nth: Vector[A] = this // nth-nth fusion
}


private
case class Reverse[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = _1.permutation{ i => _1.size - i - 1 }
    override def reverse = _1 // reverse-reverse fusion
}


private
case class Rotate[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = _1(_1.start + _2, _1.end) ++ _1(_1.start, _1.start + _2)
}
