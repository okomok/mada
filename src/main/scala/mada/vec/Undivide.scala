

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Undivide {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = {
        if (vv.isEmpty) {
            Vector.empty[A]
        } else {
            new UndivideVector(vv)
        }
    }
}

object Undivide3 {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[A] = Vector.undivide(Vector.triplesVectors(vv))
}

class UndivideVector[A](vv: Vector[Vector[A]]) extends Vector[A] {
    import Div._

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
