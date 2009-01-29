

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object LazyValues {
    def apply[A](v: Vector[A]): Vector[A] = new LazyValuesVector(v)
}

private[mada] class LazyValuesVector[A](override val underlying: Vector[A]) extends VectorAdapter[A, A] with NotWritable[A] {
    private val table = new scala.collection.jcl.HashMap[Int, A]

    override def apply(i: Int) = {
        val o = table.get(i)
        if (o.isEmpty) {
            val e = underlying(i)
            table.put(i, e)
            e
        } else {
            o.get
        }
    }

    override def lazyValues = this // lazyValues-lazyValues fusion
}
