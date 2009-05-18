

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object FromIterable {
    def apply[A](from: Iterable[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        for (e <- from.projection) {
            a.add(e)
        }
        vector.fromJclList(a)
    }
}


private[mada] object ToIterable {
    def apply[A](from: Vector[A]): Iterable[A] = new Iterable[A] {
        override def elements = new VectorIterator(from)
    }
}

private[mada] class VectorIterator[A](from: Vector[A]) extends Iterator[A] {
    private var i = from.start
    override def hasNext = i != from.end
    override def next = { val tmp = from(i); i += 1; tmp }
}
