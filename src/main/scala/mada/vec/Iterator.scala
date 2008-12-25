

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromIterator {
    def apply[A](u: Iterator[A]): Vector[A] = new IteratorVector(u)
}

class IteratorVector[A](it: Iterator[A]) extends VectorAdapter[A, A] with NotWritable[A] {
    override val * = {
        val a = new java.util.ArrayList[A]
        it.foreach(a.add(_: A))
        Vector.jclArrayListVector(a)
    }

    override def force = this

    // from-to fusion is impossible, because iterator is single-pass.
}


object VectorIterator {
    def apply[A](v: Vector[A]): Iterator[A] = new VectorIterator(v)
}

class VectorIterator[A](* : Vector[A]) extends Iterator[A] {
    private var (first, last) = *.toPair
    override def hasNext = first != last
    override def next = { val tmp = *(first); first += 1; tmp }
}
