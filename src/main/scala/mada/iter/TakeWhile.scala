

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object TakeWhile {
    def apply[A](it: Iterable[A], p: A => Boolean): Iterable[A] = Iterables.makeByName(iimpl(it.elements, p))
    def iimpl[A](it: Iterator[A], p: A => Boolean): Iterator[A] = new TakeWhileIterator(it, p)
}

private[mada] class TakeWhileIterator[A](it: Iterator[A], p: A => Boolean) extends Iterator[A] {
    private var e = new Proxies.Var[A]
    if (it.hasNext) {
        unsatisfyPredicate
    }

    override def hasNext = !e.isNull
    override def next = {
        val tmp = e.self
        unsatisfyPredicate
        tmp
    }

    private def unsatisfyPredicate: Unit = {
        val x = it.next
        if (p(x)) e.set(x) else e.resign
    }
}
