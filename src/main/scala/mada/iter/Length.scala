

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Length {
    def apply[A](it: Iterable[A]): Int = impl(it.elements)

    def impl[A](it: Iterator[A]): Int = {
        var l = 0
        while (it.hasNext) {
            l += 1
            it.next
        }
        l
    }
}
