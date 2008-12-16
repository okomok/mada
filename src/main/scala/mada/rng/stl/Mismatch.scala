

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


object Mismatch extends Mismatch; trait Mismatch extends Predefs {
    class MadaRngStlMismatch[A1](_1: Expr.Of[Rng[A1]]) {
        def stl_mismatch[A2](_2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean) = MismatchExpr(_1, _2, _3).expr
        def stl_mismatch(_2: Expr.Of[Pointer[A1]]) = MismatchExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngStlMismatch[A1](_1: Expr.Of[Rng[A1]]): MadaRngStlMismatch[A1] = new MadaRngStlMismatch[A1](_1)
}


case class MismatchExpr[A1, A2](override val _1: Expr.Of[Rng[A1]], _2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean)
        extends Expr.Method[Rng[A1], (Pointer[A1], Pointer[A2])] {
    override protected def _default = MismatchImpl(_1.eval, _2.eval, _3)
}


object MismatchImpl {
    import Pointer._

    def apply[A1, A2](r1: Rng[A1], first2: Pointer[A2], __binary_pred: (A1, A2) => Boolean): (Pointer[A1], Pointer[A2]) = {
        val (__first1, __last1) = r1.toPair
        val __first2 = first2.copyIn(Traversal.Forward)

        while (__first1 != __last1 && __binary_pred(*(__first1), *(__first2))) {
            ++(__first1)
            ++(__first2)
        }
        (__first1, __first2)
    }
}
