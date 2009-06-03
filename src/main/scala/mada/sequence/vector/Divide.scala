

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Divide[A](val _1: Vector[A], _2: Int) extends Vector[Vector[A]] {
    ThrowIf.nonpositive(_2, "_2")
    override def start = 0
    override def end = Step.count(_1.start, _1.end, _2)
    override def apply(i: Int) = {
        val cur = _1.start + i * _2
        new Region(_1, cur, Math.min(cur + _2, _1.end))
    }
    // isDefinedAt is restrictive because _1.end affects.
}
