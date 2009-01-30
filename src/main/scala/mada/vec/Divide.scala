

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Divide {
    def apply[A](v: Vector[A], n: Int): Vector[Vector[A]] = new DivideVector(v, n)
}

private[mada] class DivideVector[A](val dividend: Vector[A], stride: Int) extends Vector[Vector[A]] {
    ThrowIf.nonpositive(stride, "stride")
    override def start = 0
    override def end = StepCount(dividend.start, dividend.end, stride)
    override def apply(i: Int) = {
        val cur = dividend.start + i * stride
        new Region(dividend, cur, Math.min(cur + stride, dividend.end))
    }
    // isDefinedAt is restrictive because dividend.end affects.
}
