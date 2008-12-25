

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Lazy {
    def apply[A](v: Vector[A]): Vector[A] = new LazyVector(v)
}

class LazyVector[A](override val * : Vector[A]) extends VectorAdapter[A, A] with NotWritable[A] {
    private val map = new scala.collection.jcl.HashMap[Long, A]

    override val size = *.size
    override def apply(i: Long) = {
        val o = map.get(i)
        if (o.isEmpty) {
            val e = *(i)
            map.put(i, e)
            e
        } else {
            o.get
        }
    }

    override def lazy_ = this // lazy-lazy fusion
}
