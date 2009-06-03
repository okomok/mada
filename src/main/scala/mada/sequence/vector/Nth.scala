

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Nth[A](_1: Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.size

    override def apply(i: Int) = _1(_1.start + i)
    override def update(i: Int, e: A) = _1(_1.start + i) = e
    override def isDefinedAt(i: Int) = _1.isDefinedAt(_1.start + i)

    override def nth = this // nth-nth fusion
}
