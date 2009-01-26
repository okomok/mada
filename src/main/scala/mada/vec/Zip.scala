

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Zip {
    def apply[A, B](v: Vector[A], w: Vector[B]): Vector[(A, B)] = new ZipVector(v, w)
}

class ZipVector[A, B](v: Vector[A], w: Vector[B]) extends Vector[(A, B)] {
    ThrowIf.differentSize(v, w, "zip")
    override def size = v.size
    override def apply(i: Int) = (v(i), w(i))
    override def update(i: Int, e: (A, B)) = { v(i) = e._1; w(i) = e._2 }
}


/*
object ZipWith {
    def apply[A, B, C](v: Vector[A], w: Vector[B], f: (A, B) => C): Vector[C] = {
        ThrowIf.differentSize(v, w, "zipWith")
        (v zip w).map({ case (a, b) => f(a, b) })
    }
}
*/
