

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object IteratorFlatten {
    def apply[A](its: Iterator[Iterator[A]]): Iterator[A] = new Iterator[A] {
        private var it: Iterator[A] = Iterator.empty

        override def hasNext = {
            if (it.hasNext) {
                true
            } else if (its.hasNext) {
                it = its.next
                hasNext
            } else {
                false
            }
        }

        override def next = {
            if (it.hasNext) {
                it.next
            } else if (its.hasNext) {
                it = its.next
                next
            } else {
                throw new NoSuchElementException("next")
            }
        }
    }
}
