

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Unique {
    def apply[A](it: Iterable[A], p: (A, A) => Boolean): Iterable[A] = Iterables.byName(iimpl(it.elements, p))
    def iimpl[A](it: Iterator[A], p: (A, A) => Boolean): Iterator[A] = new UniqueIterator(it, p)
}

private[mada] class UniqueIterator[A](private var it: Iterator[A], p: (A, A) => Boolean) extends Iterator[A] {
    override def hasNext = it.hasNext
    override def next = {
        val tmp = it.next
        it = it.dropWhile{ e => p(tmp, e) }
        tmp
    }
}
