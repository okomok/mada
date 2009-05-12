

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Bounds {
    def apply[A](v: Vector[A]): Vector[A] = new BoundsVector(v)
}

private[mada] class BoundsVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] {
    override def apply(i: Int) = { throwIfOutOfBounds(i); underlying.apply(i) }
    override def update(i: Int, e: A) = { throwIfOutOfBounds(i);  underlying(i) = e }
    override def isDefinedAt(i: Int) = underlying.start <= i && i < underlying.end

    override def bounds = this // bounds-bounds fusion

    private def throwIfOutOfBounds(i: Int): Unit = {
        if (!isDefinedAt(i)) {
            throw new IndexOutOfBoundsException(i.toString)
        }
    }
}
