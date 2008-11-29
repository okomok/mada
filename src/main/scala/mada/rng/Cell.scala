
package mada.rng


//  Cell[A] <-> Expr[Rng[A]]

object CellCompatible extends CellCompatible; trait CellCompatible {
    implicit def toMadaCellRngExpr[A](from: => Cell[A]): Expr[Rng[A]] = FromCellExpr(Expr(from)).expr
    implicit def fromMadaCellRngExpr[A](from: Expr[Rng[A]]): Cell[A] = ToCellExpr(from).eval
}


// toRng

object CellToRng extends CellToRng; trait CellToRng extends Predefs {
    class MadaRngCellToRng[A](_1: Expr[Cell[A]]) {
        def toRng = FromCellExpr(_1).expr
    }
    implicit def toMadaRngCellToRng[A](_1: Expr[Cell[A]]): MadaRngCellToRng[A] = new MadaRngCellToRng[A](_1)
}

case class FromCellExpr[A](_1: Expr[Cell[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]): U = c match {
        case DefaultContext => _1 match {
            case ToCellExpr(x1) => x1.eval
            case _ => forward.eval
        }
        case _ => forward.eval(c)
    }

    private def forward = IndexAccessRngExpr(new CellIndexAccess(_1.eval.elem))
}

class CellIndexAccess[A](val base: A) extends IndexAccess[A] {
    override def _get(i: Long) = { Assert("out of CellRng", i == 0); base }
    override def _size = 1
}


// toCell

object ToCell extends ToCell; trait ToCell extends Predefs {
    class MadaRngToCell[A](_1: Expr[Rng[A]]) {
        def rng_toCell = ToCellExpr(_1).expr
    }
    implicit def toMadaRngToCell[A](_1: Expr[Rng[A]]): MadaRngToCell[A] = new MadaRngToCell[A](_1)
}

case class ToCellExpr[A](_1: Expr[Rng[A]]) extends Expr[Cell[A]] {
    override def _eval = _1 match {
        case FromCellExpr(x1) => x1.eval
        case _ => Cell(FirstExpr(_1).eval)
    }
}
