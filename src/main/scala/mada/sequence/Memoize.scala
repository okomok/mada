

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Memoize[A](_1: Sequence[A]) extends Sequence[A] {
    private val memo = new java.util.concurrent.ConcurrentHashMap[Int, () => A]

    override def begin = new Iterator[A] {
        private var i = 0
        private val t = _1.begin

        override def isEnd = !t
        override def deref = assoc.lazyGet(memo)(i)(~t)
        override def increment = { i += 1; t.++ }
    }

    override def memoize = _1.memoize // memoize-memoize fusion
}
