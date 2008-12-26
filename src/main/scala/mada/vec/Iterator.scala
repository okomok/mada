

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromIterator {
    def apply[A](u: Iterator[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        u.foreach(a.add(_: A))
        Vector.jclArrayListVector(a)
    }
}


object VectorIterator {
    def apply[A](v: Vector[A]): Iterator[A] = new VectorIterator(v)
}

class VectorIterator[A](* : Vector[A]) extends Iterator[A] {
    private var (first, last) = *.toPair
    override def hasNext = first != last
    override def next = { val tmp = *(first); first += 1; tmp }
}
