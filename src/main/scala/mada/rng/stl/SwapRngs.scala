

// SwapRngsright Shunsuke Sogame 2008-2009.
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


object SwapRngs extends SwapRngs; trait SwapRngs extends Predefs {
    class MadaRngStlSwapRngs[A](_1: Expr.Of[Rng[A]]) {
        def stl_swapRngs(_2: Expr.Of[Pointer[A]]) = SwapRngsExpr(_1, _2).expr
    }
    implicit def toMadaRngStlSwapRngs[A](_1: Expr.Of[Rng[A]]): MadaRngStlSwapRngs[A] = new MadaRngStlSwapRngs[A](_1)
}


case class SwapRngsExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Pointer[A]])
        extends Expr.Method[Rng[A], Pointer[A]] {
    override protected def _default = SwapRngsImpl(_1.eval, _2.eval)
}


object SwapRngsImpl {
    import Pointer._

    def apply[A](r1: Rng[A], first2: Pointer[A]): Pointer[A] = {
        val (__first1, __last1) = r1.toPair
        val __first2 = first2.copyIn(Traversal.Forward)

        while (__first1 != __last1) {
            __first1 swap __first2
            ++(__first1); ++(__first2)
        }
        __first2
    }
}
