

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Divide {
    def apply[A](v: Vector[A], n: Int): Vector[Vector[A]] = Vector.triplesVector(v.divide3(n))
}

object Divide3 {
    def apply[A](v: Vector[A], n: Int): Vector[Vector.Triple[A]] = new Divide3Vector(v, n)
}

class Divide3Vector[A](val dividend: Vector[A], stride: Int) extends Vector[Vector.Triple[A]] with NotWritable[Vector.Triple[A]] {
    ThrowIf.nonpositive(stride, "stride")
    override def size = StepCount(dividend.size, 0, stride)
    override def apply(i: Int) = (dividend, i * stride, Math.min((i + 1) * stride, dividend.size))
}
