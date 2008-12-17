

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


object NthElement extends NthElement; trait NthElement extends Predefs {
    class MadaRngStlNthElement[A](_1: Expr.Of[Rng[A]]) {
        def stl_nthElement(_2: Rng[A] => Pointer[A])(implicit _3: A => Ordered[A]) = NthElementExpr[A](_1, _2, _3).expr
        def stl_nthElementWith(_2: Rng[A] => Pointer[A], _3: (A, A) => Boolean) = NthElementWithExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlNthElement[A](_1: Expr.Of[Rng[A]]): MadaRngStlNthElement[A] = new MadaRngStlNthElement[A](_1)
}


case class NthElementExpr[A](_1: Expr.Of[Rng[A]], _2: Rng[A] => Pointer[A], _3: A => Ordered[A])
        extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = NthElementWithExpr[A](_1, _2, _3(_) < _)
}

case class NthElementWithExpr[A](override val _1: Expr.Of[Rng[A]], _2: Rng[A] => Pointer[A], _3: (A, A) => Boolean)
        extends Expr.Method[Rng[A], Unit] {
    override protected def _default = NthElementImpl(_1.eval, _2, _3)
}


object NthElementImpl {
    def apply[A](r: Rng[A], nth: Rng[A] => Pointer[A], __comp: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, nth(r) - __*, __last, __comp)
    }

    def apply[A](* : Pointer[A], first: Long, __nth: Long, last: Long, __comp: (A, A) => Boolean): Unit = {
        var __first = first; var __last = last

        while (__last - __first > 3) {
            val __cut = detail.UnguardedPartition(*, __first, __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
            if (__cut <= __nth) {
              __first = __cut
            } else {
              __last = __cut
            }
        }
        detail.InsertionSort(*, __first, __last, __comp)
    }
}
