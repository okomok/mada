

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


object IsHeap extends IsHeap; trait IsHeap extends Predefs {
    class MadaRngStlIsHeap[A](_1: Expr.Of[Rng[A]]) {
        def stl_isHeap(implicit _2: A => Ordered[A]) = IsHeapExpr[A](_1, _2(_) < _).expr
        def stl_isHeapWith(_2: (A, A) => Boolean) = IsHeapExpr(_1, _2).expr
    }
    implicit def toMadaRngStlIsHeap[A](_1: Expr.Of[Rng[A]]): MadaRngStlIsHeap[A] = new MadaRngStlIsHeap[A](_1)
}


case class IsHeapExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = IsHeapImpl(_1.eval, _2)
}


object IsHeapImpl {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Boolean = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __last, __comp)
    }

    def apply[A](__first : Pointer[A], __n: Long, __comp: (A, A) => Boolean): Boolean = {
        var __parent = 0L
        var __child = 1L
        while (__child < __n) {
            if (__comp(__first(__parent), __first(__child))) {
                return false
            }
            if ((__child & 1) == 0) {
                __parent += 1
            }
            __child += 1
        }
        true
    }
}
