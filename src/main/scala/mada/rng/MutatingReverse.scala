

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


package mada.rng


import Pointer._


object MutatingReverse extends MutatingReverse; trait MutatingReverse extends Predefs {
    class MadaRngMutatingReverse[A](_1: Expr.Of[Rng[A]]) {
        def mutatingReverse = MutatingReverseExpr(_1).expr
    }
    implicit def toMadaRngMutatingReverse[A](_1: Expr.Of[Rng[A]]): MadaRngMutatingReverse[A] = new MadaRngMutatingReverse[A](_1)
}


case class MutatingReverseExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = MutatingReverseImpl(_1.eval)
}


object MutatingReverseImpl {
    def apply[A](r: Rng[A]): Unit = r.traversal match {
        case _: Traversal.RandomAccess => inRandomAccess(r)
        case _: Traversal.Bidirectional => inBidirectional(r)
    }

    def inRandomAccess[A](r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        while (__first < __last) {
            __first swap --(__last)
            ++(__first)
        }
    }

    def inBidirectional[A](r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        while (__first != __last && __first != --(__last)) {
            __first swap __last
            ++(__first)
        }
    }
}
