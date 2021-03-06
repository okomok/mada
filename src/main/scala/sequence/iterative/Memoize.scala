

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


import java.util.concurrent.ConcurrentHashMap


private
case class Memoize[A](_1: Iterative[A]) extends Iterative[A] {
    private[this] val derefMemo = new ConcurrentHashMap[Int, () => A]
    //private[this] val isEndMemo = new ConcurrentHashMap[Int, () => Boolean]

    override def begin = new Iterator[A] {
        private[this] var i = 0
        private[this] val it = _1.begin

        override def isEnd = !it //assoc.lazyGet(isEndMemo)(i)(!it)
        override def deref = assoc.lazyGet(derefMemo)(i)(~it)
        override def increment() { i += 1; it.++ }
    }

    override def memoize: Iterative[A] = this // memoize.memoize fusion
}
