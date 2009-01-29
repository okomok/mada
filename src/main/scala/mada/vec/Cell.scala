

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object CellVector {
    def apply[A](from: Cell[A]): Vector[A] = new CellVector(from)
}

private[mada] class CellVector[A](from: Cell[A]) extends Vector[A] {
    override def start = 0
    override def end = 1
    override def apply(i: Int) = from.elem
}
