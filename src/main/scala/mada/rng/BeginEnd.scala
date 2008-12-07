
package mada.rng


// begin

object Begin extends Begin; trait Begin extends Predefs {
    class MadaRngBegin[A](_1: Expr.Of[Rng[A]]) {
        def begin = BeginExpr(_1).expr
    }
    implicit def toMadaRngBegin[A](_1: Expr.Of[Rng[A]]): MadaRngBegin[A] = new MadaRngBegin[A](_1)
}

case class BeginExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Pointer[A]] {
    override def _default = _1.eval.begin
}


// end

object End extends End; trait End extends Predefs {
    class MadaRngEnd[A](_1: Expr.Of[Rng[A]]) {
        def end = EndExpr(_1).expr
    }
    implicit def toMadaRngEnd[A](_1: Expr.Of[Rng[A]]): MadaRngEnd[A] = new MadaRngEnd[A](_1)
}

case class EndExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Pointer[A]] {
    override def _default = _1.eval.end
}
