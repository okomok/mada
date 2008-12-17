

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Copyright (c) 1996-1998
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


object Advance extends Advance; trait Advance extends Predefs {
    class MadaRngStlAdvance[A](_1: Expr.Of[Pointer[A]]) {
        def stl_advance(_2: Long) = AdvanceExpr(_1, _2).expr
    }
    implicit def toMadaRngStlAdvance[A](_1: Expr.Of[Pointer[A]]): MadaRngStlAdvance[A] = new MadaRngStlAdvance[A](_1)
}


case class AdvanceExpr[A](override val _1: Expr.Of[Pointer[A]], _2: Long)
        extends Expr.Method[Pointer[A], Unit] {
    override protected def _default = AdvanceImpl(_1.eval, _2)
}


object AdvanceImpl {
    import Pointer._

    def apply[A](__i: Pointer[A], __n : Long): Unit = {
        __i.traversal match {
            case _: Traversal.RandomAccess => __i += __n
            case _: Traversal.Bidirectional => inBidirectional(__i, __n)
            case _: Traversal.SinglePass => inSinglePass(__i, __n)
        }
    }

    def inSinglePass[A](__i: Pointer[A], n: Long): Unit = {
        var __n = n

        while (__n != 0) {
            ++(__i)
            __n -= 1
        }
    }

    def inBidirectional[A](__i: Pointer[A], n: Long): Unit = {
        var __n = n

        if (__n > 0) {
            while (__n != 0) {
                ++(__i)
                __n -= 1
            }
        } else {
            while (__n != 0) {
                --(__i)
                __n += 1
            }
        }
    }
}
