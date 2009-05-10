

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


@conversion
object fromIterator {
    def apply[A](from: Iterator[A]) = new Traverser[A] {
        private val e = new Proxies.Var[A]
        if (from.hasNext) {
            e.assign(from.next)
        }

        override def isEnd = e.isNull
        override def deref = e.self
        override def increment = {
            if (from.hasNext) {
                e.assign(from.next)
            } else {
                e.resign
            }

        }
    }
}


@conversion
object toIterator {
    def apply[A](from: Traverser[A]) = new Iterator[A] {
        override def hasNext = !from.isEnd
        override def next = {
            val tmp = ~from
            from.++
            tmp
        }
    }
}
