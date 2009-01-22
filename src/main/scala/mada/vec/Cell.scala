

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object CellVector {
    def apply[A](from: Cell[A]): Vector[A] = new CellVector(from)
}

class CellVector[A](from: Cell[A]) extends Vector[A] {
    override def size = 1
    override def apply(i: Int) = from.elem
}
