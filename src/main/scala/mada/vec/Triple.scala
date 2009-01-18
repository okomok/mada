

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object TripleVector {
    def apply[A](v: (Vector[A], Long, Long)): Vector[A] = v._1.window(v._2, v._3)
}


object VectorTriple {
    def apply[A](v: Vector[A]): (Vector[A], Long, Long) = (v, 0, v.size)
}
