

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object TakeWhile {
    def apply[A](it: Iterator[A])(p: A => Boolean): Iterator[A] = new TakeWhileIterator(it, p)
}

private[mada] class TakeWhileIterator[A](it: Iterator[A], p: A => Boolean) extends Iterator[A] {
    private var e: Option[A] = None
    if (it.hasNext) {
        unsatisfyPredicate
    }

    override def hasNext = !e.isEmpty
    override def next = {
        val tmp = e.get
        unsatisfyPredicate
        tmp
    }

    private def unsatisfyPredicate: Unit = {
        val x = it.next
        e = if (p(x)) Some(x) else None
    }
}
