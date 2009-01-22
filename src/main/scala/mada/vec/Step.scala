

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Step {
    def apply[A](v: Vector[A], stride: Int): Vector[A] = new StepVector(v, stride)
}

class StepVector[A](override val * : Vector[A], stride: Int) extends VectorAdapter[A, A] {
    ThrowIf.nonpositive(stride, "step stride")
    override def size = StepCount(*.size, 0, stride)
    override def mapIndex(i: Int) = i * stride
    override def step(n: Int) = *.step(stride * n) // step-step fusion
}

object StepCount {
    def apply(size: Int, start: Int, stride: Int): Int = {
        if (size == 0) {
            0
        } else {
            val i = (size - start - 1) / stride
            if (i < 0) 0 else i + 1
        }
    }
}
