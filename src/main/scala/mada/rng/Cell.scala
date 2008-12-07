
package mada.rng


import First._


//  Cell[A] <-> Expr[Rng[A]]

object CellCompatible extends CellCompatible; trait CellCompatible {
    implicit def madaRng_Cell2ExprRng[A](from: Cell[A]): Expr.Of[Rng[A]] = FromCellExpr(Expr.Constant(from)).expr
}


// toRng

object CellToRng extends CellToRng; trait CellToRng extends Predefs {
    class MadaRngCellToRng[A](_1: Expr.Of[Cell[A]]) {
        def toRng = FromCellExpr(_1).expr
    }
    implicit def toMadaRngCellToRng[A](_1: Expr.Of[Cell[A]]): MadaRngCellToRng[A] = new MadaRngCellToRng[A](_1)
}

case class FromCellExpr[A](_1: Expr.Of[Cell[A]]) extends Expr[Cell[A], Rng[A]] {
    override def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => methodOf(_1)
        case Default => _1 match {
            case ToCellExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(x)
    }

    private def delegate = IndexAccessRngExpr(new CellIndexAccess(_1.eval))
}

class CellIndexAccess[A](val base: Cell[A]) extends IndexAccess[A] {
    override def _get(i: Long) = { Assert("out of CellRng", i == 0); base.elem }
    override def _size = 1
}


// toCell

object ToCell extends ToCell; trait ToCell extends Predefs {
    class MadaRngToCell[A](_1: Expr.Of[Rng[A]]) {
        def toCell = ToCellExpr(_1).expr
    }
    implicit def toMadaRngToCell[A](_1: Expr.Of[Rng[A]]): MadaRngToCell[A] = new MadaRngToCell[A](_1)
}

case class ToCellExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Cell[A]] {
    override def _default = _1 match {
        case FromCellExpr(x1) => x1.eval
        case _ => Cell(_1.first.eval)
    }
}
