

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Zip {
    def apply[A, B](v: Vector[A], w: Vector[B]): Vector[(A, B)] = new ZipVector(v, w)
}

private[mada] class ZipVector[A, B](v: Vector[A], w: Vector[B]) extends Vector[(A, B)] {
    override def start = 0
    override def end = Math.min(v.nth.size, w.nth.size)

    override def apply(i: Int) = (v.nth(i), w.nth(i))
    override def update(i: Int, e: (A, B)) = { v.nth(i) = e._1; w.nth(i) = e._2 }
    override def isDefinedAt(i: Int) = v.nth.isDefinedAt(i) && w.nth.isDefinedAt(i)
}
