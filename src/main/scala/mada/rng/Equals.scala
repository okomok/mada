
package mada.rng


// equals

object Equals extends Equals

trait Equals extends Predefs {
    class MadaRngEquals[A1](_1: Expr[Rng[A1]]) {
        def rng_equals[A2](_2: Expr[Rng[A2]], _3: (A1, A2) => Boolean) = EqualsExpr(_1, _2, _3).expr
        def rng_equals(_2: Expr[Rng[A1]]) = EqualsExpr(_1, _2, (_: A1) == (_: A1)).expr
    }
    implicit def toMadaRngEquals[A1](_1: Expr[Rng[A1]]): MadaRngEquals[A1] = new MadaRngEquals[A1](_1)
}

case class EqualsExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Rng[A2]], _3: (A1, A2) => Boolean) extends Expr[Boolean] {
    override def _eval = {
        val z1 = _1.toLazy
        val z2 = _2.toLazy
        z1.eval.traversal upper z2.eval.traversal match {
            case RandomAccessTraversal => {
                if (SizeExpr(z1).eval != SizeExpr(z2).eval) false else EqualsToExpr(z1, BeginExpr(z2), _3).eval
            }
            case SinglePassTraversal => EqualsImpl(z1.eval, z2.eval, _3)
        }
    }
}

object EqualsImpl {
    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Boolean = {
        val (p1, q1) = (r1.begin, r1.end)
        val (p2, q2) = (r2.begin, r2.end)
        while (p1 != q1 && p2 != q2) {
            if (!f(*(p1), *(p2)))
                return false
            ++(p1); ++(p2)
        }
        (p2 == q2) && (p1 == q1)
    }
}


// equalsTo

object EqualsTo extends EqualsTo

trait EqualsTo extends Predefs {
    class MadaRngEqualsTo[A1](_1: Expr[Rng[A1]]) {
        def rng_equals[A2](_2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) = EqualsToExpr(_1, _2, _3).expr
        def rng_equals(_2: Expr[Pointer[A1]]) = EqualsToExpr(_1, _2, (_: A1) == (_: A1)).expr
    }
    implicit def toMadaRngEqualsTo[A1](_1: Expr[Rng[A1]]): MadaRngEqualsTo[A1] = new MadaRngEqualsTo[A1](_1)
}

case class EqualsToExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) extends Expr[Boolean] {
    override def _eval = {
        val z1 = _1.toLazy
        MismatchExpr(z1, _2, _3).eval._1 == EndExpr(z1).eval
    }
}
