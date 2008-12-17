

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


object Find extends Find; trait Find extends Predefs {
    class MadaRngStlFind[A](_1: Expr.Of[Rng[A]]) {
        def stl_find(_2: A => Boolean) = FindExpr(_1, _2).expr
    }
    implicit def toMadaRngStlFind[A](_1: Expr.Of[Rng[A]]): MadaRngStlFind[A] = new MadaRngStlFind[A](_1)
}


case class FindExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Pointer[A]] {
    override protected def _default = FindImpl(_1.eval, _2)
}


object FindImpl {
    import Pointer._

    def apply[A](r: Rng[A], __pred: A => Boolean): Pointer[A] = {
        val (__first, __last) = r.toPair

        while (__first != __last && !__pred(*(__first))) {
            ++(__first)
        }
        __first
    }
}
