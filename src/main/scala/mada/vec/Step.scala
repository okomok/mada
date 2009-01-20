

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Step {
    def apply[A](v: Vector[A], stride: Long): Vector[A] = new StepVector(v, stride)
}

class StepVector[A](override val * : Vector[A], stride: Long) extends VectorAdapter[A, A] {
    Assert(stride > 0)
    override def size = StepCount(*.size, 0, stride)
    override def mapIndex(i: Long) = i * stride
    override def step(n: Long) = *.step(stride * n) // step-step fusion
}

object StepCount {
    def apply(size: Long, start: Long, stride: Long): Long = {
        if (size == 0) {
            0
        } else {
            val i = (size - start - 1) / stride
            if (i < 0) 0 else i + 1
        }
    }
}
