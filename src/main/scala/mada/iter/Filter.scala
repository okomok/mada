

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Filter {
    def apply[A](it: Iterator[A])(p: A => Boolean): Iterator[A] = it match {
        case it: FilterIterator[_] => Iterators.filter(it.it){ e => it.p(e) && p(e) } // filter-filter fusion
        case _ => new FilterIterator(it, p)
    }
}

private[mada] class FilterIterator[A](val it: Iterator[A], val p: A => Boolean) extends Iterator[A] {
    private var e: Option[A] = None
    satisfyPredicate

    override def hasNext = !e.isEmpty
    override def next = {
        val tmp = e.get
        e = None
        satisfyPredicate
        tmp
    }

    private def satisfyPredicate: Unit = {
        Assert(e.isEmpty)

        while (it.hasNext) {
            val x = it.next
            if (p(x)) {
                e = Some(x)
                return
            }
        }
    }
}
