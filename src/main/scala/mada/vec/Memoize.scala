

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Memoize {
    def apply[A](v: Vector[A]): Vector[A] = new MemoizeVector(v)
}

private[mada] class MemoizeVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] with Adapter.NotWritable[A] {
    private val table = new java.util.concurrent.ConcurrentHashMap[Int, () => A]
    override def apply(i: Int) = Maps.lazyGet(table)(i){ underlying(i) }
    override def memoize = this // memoize-memoize fusion
}
