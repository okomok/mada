

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Nth {
    def apply[A](v: Vector[A]): Vector[A] = new NthVector(v)
}

private[mada] class NthVector[A](v: Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = v.size

    override def apply(i: Int) = v(v.start + i)
    override def update(i: Int, e: A) = v(v.start + i) = e
    override def isDefinedAt(i: Int) = v.isDefinedAt(v.start + i)

    override def nth = this // nth-nth fusion
}
