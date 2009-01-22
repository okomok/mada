

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Undivide {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = Vector.undivide3(Vector.triples(vv))
}

object Undivide3 {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[A] = vv match {
        case vv: Divide3Vector[_] => vv.dividend // undivide3-divide3 fusion
        case _ => {
            if (vv.isEmpty) {
                Vector.empty[A]
            } else {
                new Undivide3Vector(vv)
            }
        }
    }
}

class Undivide3Vector[A](vv: Vector[Vector.Triple[A]]) extends Vector[A] {
    override def size = (quotient * divisor) + remainder
    override def apply(i: Int) = {
        val d = divisor
        val (v, k, _) = local(i, d)
        v(k + Div.remainder(i, d))
    }
    override def update(i: Int, e: A) = {
        val d = divisor
        val (v, k, _) = local(i, d)
        v(k + Div.remainder(i, d)) = e
    }

    private def local(i: Int, d: Int): Vector.Triple[A] = vv(Div.quotient(i, d))
    private def quotient: Int = vv.size - 1
    private def divisor: Int = { val (_, i, j) = vv.first; j - i }
    private def remainder: Int = { val (_, i, j) = vv.last; j - i }
}
