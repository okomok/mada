

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private object _Step {
    def count(start: Int, end: Int, stride: Int): Int = {
        if (start == end) {
            0
        } else {
            ((end - start - 1) / stride) + 1
        }
    }
}

private[mada] case class Step[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    Precondition.positive(_2, "step")

    override protected val delegate = _1.permutation{ i => i * _2 }.nth(0, _Step.count(_1.start, _1.end, _2))

    override def step(n: Int) = _1.step(_2 * n) // step-step fusion

    // This can't keep writability.
    // override val delegate = range(0, _Step.count(_1.start, _1.end, _2)).map{ i => _1.nth(i * _2) }
}
