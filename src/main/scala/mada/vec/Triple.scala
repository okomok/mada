

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object TripleVector {
    def apply[A](v: Vector.Triple[A]): Vector[A] = v._1(v._2, v._3)
}

private[mada] object VectorTriple {
    def apply[A](v: Vector[A]): Vector.Triple[A] = (v, v.start, v.end)
}
