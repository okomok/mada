

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Step {
    def apply[A](v: Vector[A], n: Long, m: Long): Vector[A] = new StepVector(v, n, m)
}

class StepVector[A](override val * : Vector[A], start: Long, stride: Long) extends Adapter[A, A] {
    Assert(start >= 0)
    Assert(stride > 0)
    override def size = {
        if (*.size == 0) {
            0
        } else {
            val i = (*.size - start - 1) / stride
            if (i < 0) 0 else i + 1
        }
    }
    override def mapIndex(i: Long) = start + (i * stride)

    override def step(n: Long, m: Long) = *.step(start + (n * stride), stride * m) // step-step fusion
}
