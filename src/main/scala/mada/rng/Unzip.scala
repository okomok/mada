
package mada.rng


object Unzip extends Unzip; trait Unzip extends Predefs {
    class MadaRngUnzip[A1, A2](_1: Expr.Of[Rng[(A1, A2)]]) {
        def unzip = UnzipExpr(_1).expr
    }
    implicit def toMadaRngUnzip[A1, A2](_1: Expr.Of[Rng[(A1, A2)]]): MadaRngUnzip[A1, A2] = new MadaRngUnzip[A1, A2](_1)
}


case class UnzipExpr[A1, A2](override val _1: Expr.Of[Rng[(A1, A2)]]) extends Expr.Method[Rng[(A1, A2)], (Rng[A1], Rng[A2])] {
    override protected def _default = _1 match {
        case ZipExpr(x1, x2) => (x1.eval, x2.eval)
        case _ => UnzipImpl(_1.eval)
    }
}


object UnzipImpl {
    def apply[A1, A2](r: Rng[(A1, A2)]): (Rng[A1], Rng[A2]) = {
        AssertModels(r, ForwardTraversal)
        (new UnzipPointer1(r.begin) <=< new UnzipPointer1(r.end), new UnzipPointer2(r.begin) <=< new UnzipPointer2(r.end))
    }
}

class UnzipPointer1[A1, A2](override val _base: Pointer[(A1, A2)])
        extends PointerAdapter[(A1, A2), A1, UnzipPointer1[A1, A2]] {
    override protected def _read = *(base)._1
    override protected def _copy = new UnzipPointer1(base.copy)
    override def toString = new StringBuilder().append("UnzipPointer1 of ").append(base).toString
}

class UnzipPointer2[A1, A2](override val _base: Pointer[(A1, A2)])
        extends PointerAdapter[(A1, A2), A2, UnzipPointer2[A1, A2]] {
    override protected def _read = *(base)._2
    override protected def _copy = new UnzipPointer2(base.copy)
    override def toString = new StringBuilder().append("UnzipPointer2 of ").append(base).toString
}
