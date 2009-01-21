

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Undivide {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = Vector.undivide3(Vector.triples(vv))
}

object Undivide3 {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[A] = vv match {
        case vv: Divide3Vector[_] => vv.from // undivide3-divide3 fusion
        case _ => {
            if (vv.isEmpty) {
                Vector.empty[A]
            } else {
                new Undivide3Vector(vv)
            }
        }
    }
}

class Undivide3Vector[A](_vv: Vector[Vector.Triple[A]]) extends Vector[A] {
    import Div._
    private val vv = Vector.triplesVector(_vv)

    override def size = (vv.first.size * (vv.size - 1)) + vv.last.size
    override def apply(i: Int) = {
        val s = vv.first.size
        vv(quotient(i, s))(remainder(i, s))
    }
    override def update(i: Int, e: A) = {
        val s = vv.first.size
        vv(quotient(i, s))(remainder(i, s)) = e
    }
}
