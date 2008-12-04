
package mada.rng


import Begin._
import EqualsTo._
import Pointer._
import Size._


object Equals extends Equals; trait Equals extends Predefs {
    class MadaRngEquals[A1](_1: Expr[Rng[A1]]) {
        def rng_equals[A2](_2: Expr[Rng[A2]], _3: (A1, A2) => Boolean) = EqualsExpr(_1, _2, _3).expr
        def rng_equals(_2: Expr[Rng[A1]]) = EqualsExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngEquals[A1](_1: Expr[Rng[A1]]): MadaRngEquals[A1] = new MadaRngEquals[A1](_1)
}


case class EqualsExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Rng[A2]], _3: (A1, A2) => Boolean) extends Expr[Boolean] {
    override def _eval = {
        val z1 = _1.Lazy
        val z2 = _2.Lazy
        z1.eval.traversal upper z2.eval.traversal match {
            case _: RandomAccessTraversal => {
                if (z1.rng_size.eval != z2.rng_size.eval) false else z1.rng_equalsTo(z2.rng_begin, _3).eval
            }
            case _: SinglePassTraversal => EqualsImpl(z1.eval, z2.eval, _3)
        }
    }
}


object EqualsImpl {
    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Boolean = {
        val (p1, q1) = r1.toPair
        val (p2, q2) = r2.toPair
        while (p1 != q1 && p2 != q2) {
            if (!f(*(p1), *(p2)))
                return false
            ++(p1); ++(p2)
        }
        (p2 == q2) && (p1 == q1)
    }
}
