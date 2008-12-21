

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Step {
    def apply[A](v: Vector[A], n: Long): Vector[A] = new StepVector(v, n)
}

class StepVector[A](override val * : Vector[A], step: Long) extends Adapter[A, A] {
    override def size = *.size / step
    override def mapIndex(i: Long) = Math.min(*.size, i * step)
}
