

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Zip[A, B](_1: Vector[A], _2: Vector[B]) extends Vector[(A, B)] {
    override def start = 0
    override def end = Math.min(_1.nth.size, _2.nth.size)

    override def apply(i: Int) = (_1.nth(i), _2.nth(i))
    override def update(i: Int, e: (A, B)) = { _1.nth(i) = e._1; _2.nth(i) = e._2 }
    override def isDefinedAt(i: Int) = _1.nth.isDefinedAt(i) && _2.nth.isDefinedAt(i)
}
