

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object TripleVector {
    def apply[A](v: Vector.Triple[A]): Vector[A] = v._1.window(v._2, v._3)
}

object VectorTriple {
    def apply[A](v: Vector[A]): Vector.Triple[A] = (v, 0, v.size)
}


object TriplesVectors {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[Vector[A]] = vv.map({ v => Vector.tripleVector(v) })
}

object VectorsTriples {
    def apply[A](vv: Vector[Vector[A]]): Vector[Vector.Triple[A]] = vv.map({ v => v.triple })
}
