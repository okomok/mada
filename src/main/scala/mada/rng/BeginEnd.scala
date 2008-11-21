
package mada.rng


// begin

object Begin extends Begin

trait Begin extends Predefs {
    class MadaRngBegin[A](_1: Expr[Rng[A]]) {
        def rng_begin = BeginExpr(_1).expr
    }
    implicit def toMadaRngBegin[A](_1: Expr[Rng[A]]): MadaRngBegin[A] = new MadaRngBegin[A](_1)
}

case class BeginExpr[A](_1: Expr[Rng[A]]) extends Expr[Pointer[A]] {
    override def _eval = _1.eval.begin
}


// end

object End extends End

trait End extends Predefs {
    class MadaRngEnd[A](_1: Expr[Rng[A]]) {
        def rng_end = EndExpr(_1).expr
    }
    implicit def toMadaRngEnd[A](_1: Expr[Rng[A]]): MadaRngEnd[A] = new MadaRngEnd[A](_1)
}

case class EndExpr[A](_1: Expr[Rng[A]]) extends Expr[Pointer[A]] {
    override def _eval = _1.eval.end
}
