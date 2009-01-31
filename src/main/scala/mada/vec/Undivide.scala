

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Undivide {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = vv match {
        case vv: DivideVector[_] => vv.dividend // undivide-divide fusion
        case _ => {
            if (vv.isEmpty) {
                Vector.empty[A]
            } else {
                new UndivideVector(vv)
            }
        }
    }
}

private[mada] class UndivideVector[A](vv: Vector[Vector[A]]) extends Vector[A] {
    Assert(!vv.isEmpty)

    override def start = 0
    override def end = (quotient * divisor) + remainder

    override def apply(i: Int) = {
        val d = divisor
        vv.nth(Div.quotient(i, d)).nth(Div.remainder(i, d))
    }
    override def update(i: Int, e: A) = {
        val d = divisor
        vv.nth(Div.quotient(i, d)).nth(Div.remainder(i, d)) = e
    }
    override def isDefinedAt(i: Int) = {
        val d = divisor
        vv.nth.isDefinedAt(Div.quotient(i, d)) &&
        vv.nth(Div.quotient(i, d)).nth.isDefinedAt(Div.remainder(i, d))
    }

    private def quotient: Int = vv.nth.size - 1
    private def divisor: Int = vv.nth.first.size
    private def remainder: Int = vv.nth.last.size
}
