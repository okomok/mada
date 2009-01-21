

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object LazyValues {
    def apply[A](v: Vector[A]): Vector[A] = new LazyValuesVector(v)
}

class LazyValuesVector[A](override val * : Vector[A]) extends VectorAdapter[A, A] with NotWritable[A] {
    private val table = new scala.collection.jcl.HashMap[Int, A]

    override val size = *.size
    override def apply(i: Int) = {
        val o = table.get(i)
        if (o.isEmpty) {
            val e = *(i)
            table.put(i, e)
            e
        } else {
            o.get
        }
    }

    override def lazyValues = this // lazyValues-lazyValues fusion
}
