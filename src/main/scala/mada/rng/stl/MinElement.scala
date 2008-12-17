

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


object MinElement extends MinElement; trait MinElement extends Predefs {
    class MadaRngStlMinElement[A](_1: Expr.Of[Rng[A]]) {
        def stl_minElement(implicit _2: A => Ordered[A]) = MinElementExpr(_1, _2).expr
        def stl_minElementWith(_2: (A, A) => Boolean) = MinElementWithExpr(_1, _2).expr
    }
    implicit def toMadaRngStlMinElement[A](_1: Expr.Of[Rng[A]]): MadaRngStlMinElement[A] = new MadaRngStlMinElement[A](_1)
}


case class MinElementExpr[A](_1: Expr.Of[Rng[A]], _2: A => Ordered[A]) extends Expr.Alias[Rng[A], Pointer[A]] {
    override protected def _alias = MinElementWithExpr[A](_1, _2(_) < _)
}

case class MinElementWithExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Pointer[A]] {
    override protected def _default = MinElementImpl(_1.eval, _2)
}


object MinElementImpl {
    import Pointer._

    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Pointer[A] = {
        r.assertModels(Traversal.Forward)
        val (__first, __last) = r.toPair

        if (__first == __last) {
            return __first
        }
        var __result = __first.copy
        while (++(__first) != __last) {
            if (__comp(*(__first), *(__result))) {
                __result = __first.copy
            }
        }
        __result
    }
}
