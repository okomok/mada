

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Zip {
    def apply[A, B](v: Vector[A], w: Vector[B]): Vector[(A, B)] = new ZipVector(v, w)
}

private[mada] class ZipVector[A, B](v: Vector[A], w: Vector[B]) extends Vector[(A, B)] {
    private val vn = v.nth
    private val wn = w.nth

    override def start = 0
    override def end = Math.min(v.size, w.size)

    override def apply(i: Int) = (vn(i), wn(i))
    override def update(i: Int, e: (A, B)) = { vn(i) = e._1; wn(i) = e._2 }
    override def isDefinedAt(i: Int) = vn.isDefinedAt(i) && wn.isDefinedAt(i)
}
