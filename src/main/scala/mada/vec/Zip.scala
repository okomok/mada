

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Zip {
    def apply[A, B](v: Vector[A], w: Vector[B]): Vector[(A, B)] = new ZipVector(v, w)
}

private[mada] class ZipVector[A, B](v: Vector[A], w: Vector[B]) extends Vector[(A, B)] {
    ThrowIf.differentSize(v, w, "zip")
    override def start = 0
    override def end = v.size
    override def apply(i: Int) = (v(v.start + i), w(w.start + i))
    override def update(i: Int, e: (A, B)) = { v(v.start + i) = e._1; w(w.start + i) = e._2 }
}
