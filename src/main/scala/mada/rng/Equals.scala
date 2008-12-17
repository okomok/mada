

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._
import Size._
import stl.Equal._


object Equals extends Equals; trait Equals extends Predefs {
    class MadaRngEquals[A1](_1: Expr.Of[Rng[A1]]) {
        def requals[A2](_2: Expr.Of[Rng[A2]], _3: (A1, A2) => Boolean) = EqualsExpr(_1, _2, _3).expr
        def requals(_2: Expr.Of[Rng[A1]]) = EqualsExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngEquals[A1](_1: Expr.Of[Rng[A1]]): MadaRngEquals[A1] = new MadaRngEquals[A1](_1)
}


case class EqualsExpr[A1, A2](override val _1: Expr.Of[Rng[A1]], _2: Expr.Of[Rng[A2]], _3: (A1, A2) => Boolean)
        extends Expr.Method[Rng[A1], Boolean] {
    override protected def _default = EqualsImpl(_1.eval, _2.eval, _3)
}


object EqualsImpl {
    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Boolean = {
        r1.traversal upper r2.traversal match {
            case _: Traversal.RandomAccess => inRandomAccess(r1, r2, f)
            case _: Traversal.SinglePass => inSinglePass(r1, r2, f)
        }
    }

    def inRandomAccess[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Boolean = {
        if (r1./.size./ != r2./.size./) {
            false
        } else {
            r1./.stl_equalIf(r2.begin, f)./
        }
    }

    def inSinglePass[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Boolean = {
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
