

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromList {
    def apply[A](u: List[A]): Vector[A] = new ListVector(u)
}

class ListVector[A](l: List[A]) extends Adapter[A, A] with NotWritable[A] {
    override val * = {
        val a = new java.util.ArrayList[A]
        l.foreach(a.add(_: A))
        jcl.FromArrayList(a)
    }

    override def force = this
}


// List is sealed.
object ToList {
    def apply[A](v: Vector[A]): List[A] = List.fromIterator(v.toIterator)
}
