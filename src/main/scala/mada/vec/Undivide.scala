

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

    private val vvn = vv.nth

    override def start = 0
    override def end = (quotient * divisor) + remainder

    override def apply(i: Int) = {
        val d = divisor
        Nth.read( // called directly to avoid heap-allocations.
            vvn(Div.quotient(i, d)), Div.remainder(i, d) )
    }
    override def update(i: Int, e: A) = {
        val d = divisor
        Nth.write(
            vvn(Div.quotient(i, d)), Div.remainder(i, d), e )
    }
    override def isDefinedAt(i: Int) = {
        val d = divisor
        vvn.isDefinedAt(Div.quotient(i, d)) &&
        Nth.isDefinedAt(
            vvn(Div.quotient(i, d)), Div.remainder(i, d) )
    }

    private def quotient: Int = vvn.size - 1
    private def divisor: Int = vvn.first.size
    private def remainder: Int = vvn.last.size
}
