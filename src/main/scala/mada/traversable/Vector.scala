

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


/* Move to vector.

case class ToTraversable[A](_1: Vector[A]) extends Traversable[A] {
    override def begin = traverser.fromIterator(_1.elements)

    override def toVector = _1 // to-from fusion
}
*/


case class ToVector[A](_1: Traversable[A]) extends vector.Forwarder[A] {
    override val self = {
        val t = _1.begin
        val a = new java.util.ArrayList[A]
        while (t) {
            a.add(~t)
            t.++
        }
        vector.fromJclList(a)
    }

    // Add to vector
//    override def toTraversable = _1 // to-from fusion
}
