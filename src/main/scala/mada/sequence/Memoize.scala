

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


import java.util.concurrent.ConcurrentHashMap


case class Memoize[A](_1: Sequence[A]) extends Sequence[A] {
    private val derefMemo = new ConcurrentHashMap[Int, () => A]
    //private val isEndMemo = new ConcurrentHashMap[Int, () => Boolean]

    override def begin = new Iterator[A] {
        private var i = 0
        private val it = _1.begin

        override def isEnd = !it //assoc.lazyGet(isEndMemo)(i)(!it)
        override def deref = assoc.lazyGet(derefMemo)(i)(~it)
        override def increment = { i += 1; it.++ }
    }

    override def memoize: Sequence[A] = this // memoize-memoize fusion
}
