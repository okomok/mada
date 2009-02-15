

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Cut {
    def apply[A](it: Iterator[A]): Iterator[A] = new CutIterator(it)
}

private[mada] class CutIterator[A](it: Iterator[A]) extends Iterator[A] {
    override def hasNext = it.hasNext
    override def next = it.next
}
