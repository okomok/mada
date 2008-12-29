

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.ListIterator


object VectorListIterator {
    def apply[A](v: Vector[A]): ListIterator[A] = new VectorListIterator(v)
}

class VectorListIterator[A](* : Vector[A]) extends ListIterator[A] {
    private val (first, last) = *.toPair
    private var cur = 0
    override def add(e: A) = throw new UnsupportedOperationException
    override def hasNext = cur != last
    override def hasPrevious = cur != first
    override def next = { val tmp = *(cur); cur += 1; tmp }
    override def nextIndex = cur
    override def previous = { cur -= 1; *(cur) }
    override def previousIndex = cur - 1
    override def remove = throw new UnsupportedOperationException
    override def set(e: A) = throw new UnsupportedOperationException
}
