
package mada.rng


object Mismatch extends Mismatch

trait Mismatch extends Predefs {
    class MadaRngMismatch[A1](_1: Expr[Rng[A1]]) {
        def rng_mismatch[A2](_2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) = MismatchExpr(_1, _2, _3).expr
        def rng_mismatch(_2: Expr[Pointer[A1]]) = MismatchExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngMismatch[A1](_1: Expr[Rng[A1]]): MadaRngMismatch[A1] = new MadaRngMismatch[A1](_1)
}


case class MismatchExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) extends Expr[(Pointer[A1], Pointer[A2])] {
    override def _eval = MismatchImpl(_1.eval, _2.eval, _3)
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
