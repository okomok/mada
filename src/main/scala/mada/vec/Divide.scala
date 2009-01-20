

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Divide {
    def apply[A](v: Vector[A], n: Long): Vector[Vector[A]] = v.divide3(n).map({ w => Vector.tripleVector(w) })
}

object Divide3 {
    def apply[A](v: Vector[A], n: Long): Vector[Vector.Triple[A]] = new Divide3Vector(v, n)
}

class Divide3Vector[A](v: Vector[A], stride: Long) extends Vector[Vector.Triple[A]] with NotWritable[Vector.Triple[A]] {
    Assert(stride > 0)
    override def size = StepCount(v.size, 0, stride)
    override def apply(i: Long) = (v, i * stride, Math.min((i + 1) * stride, v.size))
}
