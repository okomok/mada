

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Seal {
    def apply[A](it: Iterable[A]): Iterable[A] = Iterables.makeByName(impl(it.elements))
    def impl[A](it: Iterator[A]): Iterator[A] = new SealIterator(it)
}

private[mada] sealed class SealIterator[A](it: Iterator[A]) extends Iterator[A] {
    override def hasNext = it.hasNext
    override def next = it.next
}
