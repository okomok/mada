

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


// WILL BE REMOVED.
case class FromVector[A](_1: Vector[A]) extends Sequence[A] {
    override def begin = iterator.fromSIterator(_1.elements)

    // Never fusion: newly allocated vector is always provided.
    // override def _toVector[B](_this: Sequence[B]) = _1.asInstanceOf[Vector[B]] // to-from fusion
}

case class ToVector[A](_1: Sequence[A]) extends vector.Forwarder[A] {
    override protected lazy val delegate = {
        val it = _1.begin
        val a = new java.util.ArrayList[A]
        while (it) {
            a.add(~it)
            it.++
        }
        vector.fromJclList(a)
    }
}
