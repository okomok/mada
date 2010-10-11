

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Memoize[A](_1: Vector[A]) extends TransformAdapter[A] with NotWritable[A] {
    override protected val underlying = _1

    private val table = new java.util.concurrent.ConcurrentHashMap[Int, () => A]
    override def apply(i: Int) = assoc.lazyGet(table)(i){ underlying(i) }

    override def memoize = this // memoize-memoize fusion
}
