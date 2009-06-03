

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector.jcl


private[mada] object IterableFromVector {
    def apply[A](v: Vector[A]): java.lang.Iterable[A] = new java.lang.Iterable[A] {
        override def iterator = new VectorListIterator(v)
    }
}

private[mada] class VectorListIterator[A](v: Vector[A]) extends java.util.ListIterator[A] {
    private var cur = v.start

    override def add(e: A) = throw new UnsupportedOperationException
    override def hasNext = cur != v.end
    override def hasPrevious = cur != v.start

    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = v(cur)
        cur += 1
        tmp
    }

    override def nextIndex = cur

    override def previous = {
        if (!hasPrevious) {
            throw new NoSuchElementException("previous")
        }
        cur -= 1
        v(cur)
    }

    override def previousIndex = cur - 1
    override def remove = throw new UnsupportedOperationException("ToListIterator.remove")
    override def set(e: A) = throw new UnsupportedOperationException("ToListIterator.set")
}


private[mada] object IterableToVector {
    def apply[A](from: java.lang.Iterable[A]): Vector[A] = iimpl(from.iterator)

    def iimpl[A](from: java.util.Iterator[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        while (from.hasNext) {
            a.add(from.next)
        }
        vector.fromJclList(a)
    }
}
