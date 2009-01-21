

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.ListIterator


object VectorListIterator {
    def apply[A](v: Vector[A]): ListIterator[A] = new VectorListIterator(v)
}

class VectorListIterator[A](v: Vector[A]) extends ListIterator[A] {
    private val (x, first, last) = v.triple
    private var cur = first

    override def add(e: A) = throw new UnsupportedOperationException
    override def hasNext = cur != last
    override def hasPrevious = cur != first

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
