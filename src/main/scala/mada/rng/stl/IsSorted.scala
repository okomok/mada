

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


object IsSorted extends IsSorted; trait IsSorted extends Predefs {
    class MadaRngStlIsSorted[A](_1: Expr.Of[Rng[A]]) {
        def stl_isSorted(implicit _2: A => Ordered[A]) = IsSortedExpr[A](_1, _2(_) < _).expr
        def stl_isSortedWith(_2: (A, A) => Boolean) = IsSortedExpr(_1, _2).expr
    }
    implicit def toMadaRngStlIsSorted[A](_1: Expr.Of[Rng[A]]): MadaRngStlIsSorted[A] = new MadaRngStlIsSorted[A](_1)
}


case class IsSortedExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = IsSortedImpl(_1.eval, _2)
}


object IsSortedImpl {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Boolean = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, __last, __comp)
    }

    def apply[A](* : Pointer[A], first: Long, __last: Long, __comp: (A, A) => Boolean): Boolean = {
        var __first = first

        if (__first == __last) {
            return true
        }

        var __next = __first
        __next += 1
        while (__next != __last) {
            if (__comp(*(__next), *(__first))) {
                return false
            }
            __first = __next
            __next += 1
        }
        true
    }
}
