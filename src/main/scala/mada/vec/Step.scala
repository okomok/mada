

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Step {
    def apply[A](v: Vector[A], stride: Int): Vector[A] = new StepVector(v, stride)
}

private[mada] class StepVector[A](v: Vector[A], stride: Int) extends Adapter.Proxy[A] {
    ThrowIf.nonpositive(stride, "step stride")
    // This can't keep writability.
    // override val self = Vectors.range(0, StepCount(v.start, v.end, stride)).map({ i => v.nth(i * stride) })
    override val self = v.permutation({ i => i * stride }).nth(0, StepCount(v.start, v.end, stride))
    override def step(n: Int) = v.step(stride * n) // step-step fusion
}

private[mada] object StepCount {
    def apply(start: Int, end: Int, stride: Int): Int = {
        if (start == end) {
            0
        } else {
            ((end - start - 1) / stride) + 1
        }
    }
}
