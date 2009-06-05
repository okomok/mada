

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Divide[A](val _1: Vector[A], _2: Int) extends Vector[Vector[A]] {
    precondition.positive(_2, "stride")

    override def start = 0
    override def end = Step.count(_1.start, _1.end, _2)
    override def apply(i: Int) = {
        val cur = _1.start + i * _2
        new Region(_1, cur, Math.min(cur + _2, _1.end))
    }
    // isDefinedAt is restrictive because _1.end affects.
}


case class Undivide[A](_1: Vector[Vector[A]]) extends Forwarder[A] {
    override protected val delegate: Vector[A] = _1 match {
        case Divide(dividend, _) => dividend // undivide-divide fusion
        case _ => {
            if (_1.isEmpty) {
                vector.empty[A]
            } else {
                new _Undivide(_1)
            }
        }
    }
}

private class _Undivide[A](_1: Vector[Vector[A]]) extends Vector[A] {
    util.assert(!_1.isEmpty)

    override def start = 0
    override def end = (quotient * divisor) + remainder

    override def apply(i: Int) = {
        val d = divisor
        _1.nth(Div.quotient(i, d)).nth(Div.remainder(i, d))
    }
    override def update(i: Int, e: A) = {
        val d = divisor
        _1.nth(Div.quotient(i, d)).nth(Div.remainder(i, d)) = e
    }
    override def isDefinedAt(i: Int) = {
        val d = divisor
        _1.nth.isDefinedAt(Div.quotient(i, d)) &&
        _1.nth(Div.quotient(i, d)).nth.isDefinedAt(Div.remainder(i, d))
    }

    private def quotient: Int = _1.nth.size - 1
    private def divisor: Int = _1.nth.head.size
    private def remainder: Int = _1.nth.last.size
}