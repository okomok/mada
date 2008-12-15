

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


import Pointer._


object Reverse extends Reverse; trait Reverse extends Predefs {
    class MadaRngStlReverse[A](_1: Expr.Of[Rng[A]]) {
        def stl_reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaRngStlReverse[A](_1: Expr.Of[Rng[A]]): MadaRngStlReverse[A] = new MadaRngStlReverse[A](_1)
}


case class ReverseExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = ReverseImpl(_1.eval)
}


object ReverseImpl {
    def apply[A](r: Rng[A]): Unit = r.traversal match {
        case _: Traversal.RandomAccess => inRandomAccess(r)
        case _: Traversal.Bidirectional => inBidirectional(r)
    }

    def inRandomAccess[A](r: Rng[A]): Unit = {
        var (__*, __first, __last) = r.toTriple

        while (__first < __last) {
            __last -= 1
            __*.swap(__first, __last)
            __first += 1
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
