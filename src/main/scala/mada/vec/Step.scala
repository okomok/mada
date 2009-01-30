

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Step {
    def apply[A](v: Vector[A], stride: Int): Vector[A] = new StepVector(v, stride)
}

private[mada] class StepVector[A](override val underlying: Vector[A], stride: Int) extends VectorAdapter[A, A] {
    ThrowIf.nonpositive(stride, "step stride")
    override def start = 0
    override def end = StepCount(underlying.start, underlying.end, stride)
    override def mapIndex(i: Int) = underlying.start + i * stride

    override def step(n: Int) = underlying.step(stride * n) // step-step fusion
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
