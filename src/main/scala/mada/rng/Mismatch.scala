
package mada.rng


object Mismatch extends Mismatch

trait Mismatch {
    class MadaRngMismatch[A1](_1: Expr[Rng[A1]]) {
        def equals[A2](_2: Expr[Pointer[A2]], _3: Expr[(A1, A2) => Boolean]) = MismatchExpr(_1, _2, _3).expr
        def equals(_2: Expr[Pointer[A1]]) = MismatchExpr(_1, _2, Expr((_: A1) == (_: A1))).expr
    }
    implicit def toMadaRngMismatch[A1](_1: Expr[Rng[A1]]) = new MadaRngMismatch(_1)
}


case class MismatchExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Pointer[A2]], _3: Expr[(A1, A2) => Boolean]) extends Expr[(Pointer[A1], Pointer[A2])] {
    def eval = MismatchImpl(_1.eval, _2.eval, _3.eval)
}


object MismatchImpl {
    def apply[A1, A2](r1: Rng[A1], p2: Pointer[A2], f: (A1, A2) => Boolean): (Pointer[A1], Pointer[A2]) = {
        val (p1, q1) = (r1.begin, r1.end)
        while (p1 != q1 && f(*(p1), *(p2))) {
            ++(p1); ++(p2)
        }
        (p1, p2)
    }
}
