

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.ListIterator


private[mada] object VectorListIterator {
    def apply[A](v: Vector[A]): ListIterator[A] = new VectorListIterator(v)
}

private[mada] class VectorListIterator[A](v: Vector[A]) extends ListIterator[A] {
    private val (x, i, j) = v.triple
    private var cur = i

    override def add(e: A) = throw new UnsupportedOperationException
    override def hasNext = cur != j
    override def hasPrevious = cur != i

    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = x(cur)
        cur += 1
        tmp
    }

    override def nextIndex = cur

    override def previous = {
        if (!hasPrevious) {
            throw new NoSuchElementException("previous")
        }
        cur -= 1
        x(cur)
    }

    override def previousIndex = cur - 1
    override def remove = throw new UnsupportedOperationException("VectorListIterator.remove")
    override def set(e: A) = throw new UnsupportedOperationException("VectorListIterator.set")
}