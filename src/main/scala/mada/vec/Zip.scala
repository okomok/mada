

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Zip {
    def apply[A, B](v: Vector[A], w: Vector[B]): Vector[(A, B)] = new ZipVector(v, w)
}

class ZipVector[A, B](v: Vector[A], w: Vector[B]) extends Vector[(A, B)] {
    override def size = v.size
    override def apply(i: Long) = (v(i), w(i)) // will be suboptimal.
    override def update(i: Long, e: (A, B)) = { v(i) = e._1; w(i) = e._2 }
}
