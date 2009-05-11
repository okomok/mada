

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traverser {

    @aliasOf("Traversable")
    type Type[+A] = Traverser[A]

    @aliasOf("Traversable.Compatibles")
    val compatibles = Traverser.Compatibles

    @returnThat
    def from[A](to: Traverser[A]) = to

    @conversion
    def fromIterator[A](from: Iterator[A]) = new Traverser[A] {
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

    @conversion
    def toIterator[A](from: Traverser[A]) = new Iterator[A] {
        override def hasNext = !from.isEnd
        override def next = {
            val tmp = ~from
            from.++
            tmp
        }
    }

}
