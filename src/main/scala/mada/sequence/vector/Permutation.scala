

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Permutation[A](_1: Vector[A], _2: Int => Int) extends Adapter.Transform[A] {
    override val underlying = _1.nth

    override def apply(i: Int) = underlying(_2(i))
    override def update(i: Int, e: A) = underlying(_2(i)) = e
    override def isDefinedAt(i: Int) = underlying.isDefinedAt(_2(i))
}
