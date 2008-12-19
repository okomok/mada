

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


package mada.vec.stl


object CopyBackward extends CopyBackward; trait CopyBackward extends Predefs {
    class MadaVecStlCopyBackward[From](_1: Expr.Of[Vector[From]]) {
        def stl_copyBackward[To >: From](_2: Expr.Of[Vector[To]]) = CopyBackwardExpr(_1, _2).expr
    }
    implicit def toMadaVecStlCopyBackward[From](_1: Expr.Of[Vector[From]]): MadaVecStlCopyBackward[From] = new MadaVecStlCopyBackward[From](_1)
}


case class CopyBackwardExpr[From, To >: From](override val _1: Expr.Of[Vector[From]], _2: Expr.Of[Vector[To]]) extends Expr.Method[Vector[From], Long] {
    override protected def _default = {
        val (v1, i1, j1) = _1.eval.toTriple
        val (v2, i2, j2) = _2.eval.toTriple
        CopyBackwardImpl(v1, i1, j1, v2, j2)
    }
}


object CopyBackwardImpl {
    def apply[From, To >: From](v1 : Vector[From], __first: Long, last: Long, v2: Vector[To], result: Long): Long = {
        var __last = last
        var __result = result

        var __n = __last - __first
        while (__n > 0) {
            __result -= 1; __last -= 1
            v2(__result) = v1(__last)
            __n -= 1
        }
        __result
    }
}
