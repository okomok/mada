

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Includes extends Includes; trait Includes extends Predefs {
    class MadaRngStlIncludes[A](_1: Expr.Of[Rng[A]]) {
        def requals(_2: Expr.Of[Rng[A]], _3: (A, A) => Boolean) = IncludesExpr(_1, _2, _3).expr
        def requals(_2: Expr.Of[Rng[A]]) = IncludesExpr[A](_1, _2, _ == _).expr
    }
    implicit def toMadaRngStlIncludes[A](_1: Expr.Of[Rng[A]]): MadaRngStlIncludes[A] = new MadaRngStlIncludes[A](_1)
}


case class IncludesExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[A]], _3: (A, A) => Boolean)
        extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = IncludesImpl(_1.eval, _2.eval, _3)
}


object IncludesImpl {
    import Pointer._
    import Traversal._

    def apply[A](r1: Rng[A], r2: Rng[A], __comp: (A, A) => Boolean): Boolean = {
        r1.traversal upper r2.traversal match {
            case _: RandomAccess => inRandomAccess(r1, r2, __comp)
            case _: SinglePass => inSinglePass(r1, r2, __comp)
        }
    }

    def inSinglePass[A](r1: Rng[A], r2: Rng[A], __comp: (A, A) => Boolean): Boolean = {
        val (__first1, __last1) = r1.toPair
        val (__first2, __last2) = r2.toPair

        while (__first1 != __last1 && __first2 != __last2) {
            val elem1 = *(__first1); val elem2 = *(__first2)
            if (__comp(elem2, elem1)) {
                return false
            }  else if (__comp(elem1, elem2)) {
              ++(__first1)
            } else {
              ++(__first1); ++(__first2)
            }
        }

        return __first2 == __last2
    }

    def inRandomAccess[A](r1: Rng[A], r2: Rng[A], __comp: (A, A) => Boolean): Boolean = {
        var (_1_*, __first1, __last1) = r1.toTriple
        var (_2_*, __first2, __last2) = r2.toTriple

        if (__last1 > __last2) {
            return false
        }

        while (__first1 != __last1 && __first2 != __last2) {
            val elem1 = _1_*(__first1); val elem2 = _2_*(__first2)
            if (__comp(elem2, elem1)) {
                return false
            }  else if (__comp(elem1, elem2)) {
              __first1 += 1
            } else {
              __first1 += 1; __first2 += 1
            }
        }

        return __first2 == __last2
    }
}
