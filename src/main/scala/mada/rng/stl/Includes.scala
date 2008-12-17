

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Copyright (c) 1996,1997
 * Silicon Graphics Computer Systems, Inc.
 *
 * Copyright (c) 1997
 * Moscow Center for SPARC Technology
 *
 * Copyright (c) 1999
 * Boris Fomitchev
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 * Permission to use or copy this software for any purpose is hereby granted
 * without fee, provided the above notices are retained on all copies.
 * Permission to modify the code and to distribute modified code is granted,
 * provided the above notices are retained, and a notice that the code was
 * modified is included with the above copyright notice.
 *
 */


package mada.rng.stl


object Includes extends Includes; trait Includes extends Predefs {
    class MadaRngStlIncludes[A](_1: Expr.Of[Rng[A]]) {
        def stl_includes(_2: Expr.Of[Rng[A]]) = IncludesExpr(_1, _2).expr
        def stl_includesIf(_2: Expr.Of[Rng[A]], _3: (A, A) => Boolean) = IncludesIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlIncludes[A](_1: Expr.Of[Rng[A]]): MadaRngStlIncludes[A] = new MadaRngStlIncludes[A](_1)
}


case class IncludesExpr[A](_1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[A]]) extends Expr.Alias[Rng[A], Boolean] {
    override protected def _alias = IncludesIfExpr[A](_1, _2, _ == _)
}

case class IncludesIfExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[A]], _3: (A, A) => Boolean)
        extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = IncludesIfImpl(_1.eval, _2.eval, _3)
}


object IncludesIfImpl {
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
            } else if (__comp(elem1, elem2)) {
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
            } else if (__comp(elem1, elem2)) {
              __first1 += 1
            } else {
              __first1 += 1; __first2 += 1
            }
        }

        return __first2 == __last2
    }
}
