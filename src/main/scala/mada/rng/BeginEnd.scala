
package mada.rng


// begin

object Begin extends Begin

trait Begin {
    class MadaRngBegin[A](_1: Expr[Rng[A]]) {
        def begin = BeginExpr(_1).expr
    }
    implicit def toMadaRngBegin[A](_1: Expr[Rng[A]]) = new MadaRngBegin(_1)
}

case class BeginExpr[A](_1: Expr[Rng[A]]) extends Expr[Pointer[A]] {
    def eval = _1.eval.begin
}


// end

object End extends End

trait End {
    class MadaRngEnd[A](_1: Expr[Rng[A]]) {
        def end = EndExpr(_1).expr
    }
    implicit def toMadaRngEnd[A](_1: Expr[Rng[A]]) = new MadaRngEnd(_1)
}

case class EndExpr[A](_1: Expr[Rng[A]]) extends Expr[Pointer[A]] {
    def eval = _1.eval.end
}
