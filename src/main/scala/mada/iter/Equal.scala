

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object EqualBy {
    def apply[A, B](it: Iterable[A], jt: Iterable[B], p: Functions.Predicate2[A, B]): Boolean = {
        iimpl(it.elements, jt.elements, p)
    }

    def iimpl[A, B](it: Iterator[A], jt: Iterator[B], p: Functions.Predicate2[A, B]): Boolean = {
        while (it.hasNext && jt.hasNext) {
            if (!p(it.next, jt.next)) {
                return false
            }
        }
        !it.hasNext && !jt.hasNext
    }
}
