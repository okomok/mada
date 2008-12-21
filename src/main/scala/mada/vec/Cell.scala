

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromCell {
    def apply[A](u: Cell[A]): Vector[A] = new CellVector(u)
}

class CellVector[A](val cell: Cell[A]) extends Vector[A] {
    override def size = 1
    override def apply(i: Long) = cell.elem

    override def toCell = cell
}


object ToCell {
    def apply[A](v: Vector[A]): Cell[A] = Cell(v.first)
}
