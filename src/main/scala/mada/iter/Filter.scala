

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Filter {
    def apply[A](it: Iterable[A], p: A => Boolean): Iterable[A] = it match {
        case it: FilterIterable[_] => Iterables.filter(it.it){ e => it.p(e) && p(e) } // filter-filter fusion
        case _ => new FilterIterable(it, p)
    }
}


private[mada] class FilterIterable[A](val it: Iterable[A], val p: A => Boolean) extends Iterable/*.Projection*/[A] {
    override def elements = new FilterIterator(it.elements, p)
}


private[mada] class FilterIterator[A](it: Iterator[A], p: A => Boolean) extends Iterator[A] {
    private var e = new Proxies.Var[A]
    satisfyPredicate

    override def hasNext = !e.isNull
    override def next = {
        val tmp = e.self
        e.resign
        satisfyPredicate
        tmp
    }

    private def satisfyPredicate: Unit = {
        Assert(e.isNull)

        while (it.hasNext) {
            val x = it.next
            if (p(x)) {
                e.set(x)
                return
            }
        }
    }
}
