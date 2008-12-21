

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


class IteratorVector[A](val iterator: Iterator[A]) extends Adapter[A, A] {
    private val a = new java.util.ArrayList[A]
    override val * = {
        iterator.foreach(a.add(_: A))
        Vector.fromJclArrayList(a)
    }

    override def toJclArrayList = a
    override def toIterator = iterator
}


class VectorIterator[A](v: Vector[A]) extends Iterator[A] {
    private var (first, last) = v.toPair
    override def hasNext = first != last
    override def next = { val tmp = v(first); first += 1; tmp }
}
