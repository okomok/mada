

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Step {
    def apply[A](v: Vector[A], stride: Int): Vector[A] = new StepVector(v, stride)
}

private[mada] class StepVector[A](v: Vector[A], stride: Int) extends VectorProxy[A] {
    ThrowIf.nonpositive(stride, "step stride")
    // This can't keep writability.
    // override val self = Vector.range(0, StepCount(v.start, v.end, stride)).map({ i => v.nth(i * stride) })
    override val self = v.permutation({ i => i * stride }).nth(0, StepCount(v.start, v.end, stride))
    override def step(n: Int) = v.step(stride * n) // step-step fusion
}

private[mada] object StepCount {
    def apply(start: Int, end: Int, stride: Int): Int = {
        if (start == end) {
            0
        } else {
            val i = (end - start - 1) / stride
            if (i < 0) 0 else i + 1
        }
    }
}
