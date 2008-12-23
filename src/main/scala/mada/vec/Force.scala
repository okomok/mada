

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Force {
    def apply[A](v: Vector[A]): Vector[A] = new ForceVector(v)
}

class ForceVector[A](v: Vector[A]) extends Adapter[A, A] with NotWritable[A] {
    override val * = FromArray(v.toArray)
    override def force = this // force-force fusion
}
