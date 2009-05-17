

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/* Move to vector.

case class ToSequence[A](_1: Vector[A]) extends Sequence[A] {
    override def begin = iterator.fromIterator(_1.elements)

    override def toVector = _1 // to-from fusion
}
*/


case class ToVector[A](_1: Sequence[A]) extends vector.Forwarder[A] {
    override val self = {
        val it = _1.begin
        val a = new java.util.ArrayList[A]
        while (it) {
            a.add(~it)
            it.++
        }
        vector.fromJclList(a)
    }

    // Add to vector
//    override def toSequence = _1 // to-from fusion
}
