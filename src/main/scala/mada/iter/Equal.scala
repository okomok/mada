

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object EqualIf {
    def apply[A, B](it: Iterable[A], jt: Iterable[B], p: (A, B) => Boolean): Boolean = {
        iimpl(it.elements, jt.elements, p)
    }

    def iimpl[A, B](it: Iterator[A], jt: Iterator[B], p: (A, B) => Boolean): Boolean = {
        while (it.hasNext && jt.hasNext) {
            if (!p(it.next, jt.next)) {
                return false
            }
        }
        !it.hasNext && !jt.hasNext
    }
}
