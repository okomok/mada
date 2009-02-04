

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object FromIterator {
    def apply[A](from: Iterator[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        for (e <- from) {
            a.add(e)
        }
        Vector.fromJclList(a)
    }
}


private[mada] object ToIterator {
    def apply[A](from: Vector[A]): Iterator[A] = new VectorIterator(from)
}

private[mada] class VectorIterator[A](from: Vector[A]) extends Iterator[A] {
    private var i = from.start
    override def hasNext = i != from.end
    override def next = { val tmp = from(i); i += 1; tmp }
}
