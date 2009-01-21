

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

class UndivideVector[A](vv: Vector[Vector[A]]) extends Vector[A] {
    override def size = (vv.first.size * (vv.size - 1)) + vv.last.size
    override def apply(i: Int) = {
        val (q, r) = Div(i, vv.first.size)
        vv(q)(r)
    }
    override def update(i: Int, e: A) = {
        val (q, r) = Div(i, vv.first.size)
        vv(q)(r) = e
    }
}
