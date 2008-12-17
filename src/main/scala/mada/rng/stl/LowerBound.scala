

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


object LowerBound extends LowerBound; trait LowerBound extends Predefs {
    class MadaRngStlLowerBound[A](_1: Expr.Of[Rng[A]]) {
        def stl_lowerBound(_2: A)(implicit _3: A => Ordered[A]) = LowerBoundExpr(_1, _2, _3).expr
        def stl_lowerBoundWith(_2: A, _3: (A, A) => Boolean) = LowerBoundWithExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlLowerBound[A](_1: Expr.Of[Rng[A]]): MadaRngStlLowerBound[A] = new MadaRngStlLowerBound[A](_1)
}


case class LowerBoundExpr[A](_1: Expr.Of[Rng[A]], _2: A, _3: A => Ordered[A])
        extends Expr.Alias[Rng[A], Pointer[A]] {
    override protected def _alias = LowerBoundWithExpr[A](_1, _2, _3(_) < _)
}

case class LowerBoundWithExpr[A](override val _1: Expr.Of[Rng[A]], _2: A, _3: (A, A) => Boolean)
        extends Expr.Method[Rng[A], Pointer[A]] {
    override protected def _default = LowerBoundImpl(_1.eval, _2, _3)
}


object LowerBoundImpl {
    import Advance._
    import Distance._
    import Pointer._

    def apply[A](r: Rng[A], __val: A, __comp: (A, A) => Boolean): Pointer[A] = {
        r.assertModels(Traversal.Forward)
        val (__first, __last) = r.toPair
        apply(__first, __last, __val, __comp, __comp)
    }

    def apply[A](first: Pointer[A], __last: Pointer[A], __val: A, __comp1: (A, A) => Boolean, __comp2: (A, A) => Boolean): Pointer[A] = {
        var __first = first
        var __len = (__first <=< __last)./.stl_distance./
        var __half: Long = 0
        var __middle: Pointer[A] = null

        while (__len > 0) {
            __half = __len >> 1
            __middle = __first.copy
            __middle./.stl_advance(__half)./
            if (__comp1(*(__middle), __val)) {
                __first = __middle.copy
                ++(__first)
                __len = __len - __half - 1
            } else {
                __len = __half
            }
        }
        return __first
    }
}
