

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromIterator {
    def apply[A](u: Iterator[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        for (e <- u) {
            a.add(e)
        }
        Vector.jclListVector(a)
    }
}


object VectorIterator {
    def apply[A](v: Vector[A]): Iterator[A] = new VectorIterator(v)
}

class VectorIterator[A](v: Vector[A]) extends Iterator[A] {
    private var (x, i, j) = v.triple
    override def hasNext = i != j
    override def next = { val tmp = x(i); i += 1; tmp }
}
