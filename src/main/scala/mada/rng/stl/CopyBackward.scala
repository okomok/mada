

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


object CopyBackward extends CopyBackward; trait CopyBackward extends Predefs {
    class MadaRngStlCopyBackward[From](_1: Expr.Of[Rng[From]]) {
        def stl_copyBackward[To >: From](_2: Expr.Of[Pointer[To]]) = CopyBackwardExpr(_1, _2).expr
    }
    implicit def toMadaRngStlCopyBackward[From](_1: Expr.Of[Rng[From]]): MadaRngStlCopyBackward[From] = new MadaRngStlCopyBackward[From](_1)
}


case class CopyBackwardExpr[From, To >: From](override val _1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]])
        extends Expr.Method[Rng[From], Pointer[To]] {
    override protected def _default = CopyBackwardImpl(_1.eval, _2.eval)
}


object CopyBackwardImpl {
    import Pointer._
    import Traversal._

    def apply[From, To >: From](r1: Rng[From], q2: Pointer[To]): Pointer[To] = {
        r1.assertModels(Bidirectional)
        q2.assertModels(Bidirectional)

        r1.traversal match {
            case _: RandomAccess => inRandomAccess(r1, q2)
            case _: Bidirectional => inBidirectional(r1, q2)
        }
    }

    def inBidirectional[From, To >: From](r: Rng[From], __result: Pointer[To]): Pointer[To] = {
        val (__first, __last) = r.toPair

        while (__first != __last) {
            *(--(__result)) = *(--(__last))
        }
        __result
    }

    def inRandomAccess[From, To >: From](r: Rng[From], __result: Pointer[To]): Pointer[To] = {
        val (__first, __last) = r.toPair

        var __n = __last - __first
        while (__n > 0) {
            *(--(__result)) = *(--(__last))
            __n -= 1
        }
        __result
    }

    def inRandomAccess[A](* : Pointer[A], __first: Long, last: Long, result: Long): Long = {
        var __last = last
        var __result = result

        var __n = __last - __first
        while (__n > 0) {
            __result -= 1; __last -= 1
            *(__result) = *(__last)
            __n -= 1
        }
        return __result
    }
}
