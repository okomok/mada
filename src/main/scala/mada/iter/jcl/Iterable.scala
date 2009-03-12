

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.jcl


private[mada] object IterableToIterable {
    def apply[A](from: java.lang.Iterable[A]): Iterable[A] = Iterables.byName(iimpl(from.iterator))

    def iimpl[A](from: java.util.Iterator[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasNext
        override def next = from.next
    }
}


private[mada] object IterableFromIterable {
    def apply[A](from: Iterable[A]): java.lang.Iterable[A] = new java.lang.Iterable[A] {
        override def iterator = iimpl(from.elements)
    }

    def iimpl[A](from: Iterator[A]): java.util.Iterator[A] = new java.util.Iterator[A] {
        override def hasNext = from.hasNext
        override def next = from.next
        override def remove = throw new UnsupportedOperationException
    }
}
