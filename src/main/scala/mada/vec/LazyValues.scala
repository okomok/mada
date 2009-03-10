

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object LazyValues {
    def apply[A](v: Vector[A]): Vector[A] = new LazyValuesVector(v)
}

private[mada] class LazyValuesVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] with Adapter.NotWritable[A] {
    private val table = new scala.collection.jcl.HashMap[Int, A]
    override def apply(i: Int) = Maps.lazyGet(table)(i){ underlying(i) }
    override def lazyValues = this // lazyValues-lazyValues fusion
}
