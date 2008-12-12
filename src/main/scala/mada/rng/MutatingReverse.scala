
package mada.rng


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Permission to use, copy, modify, distribute and sell this software
 * and its documentation for any purpose is hereby granted without fee,
 * provided that the above copyright notice appear in all copies and
 * that both that copyright notice and this permission notice appear
 * in supporting documentation.  Hewlett-Packard Company makes no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied warranty.
 *
 *
 * Copyright (c) 1996
 * Silicon Graphics Computer Systems, Inc.
 *
 * Permission to use, copy, modify, distribute and sell this software
 * and its documentation for any purpose is hereby granted without fee,
 * provided that the above copyright notice appear in all copies and
 * that both that copyright notice and this permission notice appear
 * in supporting documentation.  Silicon Graphics makes no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied warranty.
 */


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
        case _: RandomAccessTraversal => inRandomAccess(r)
        case _: BidirectionalTraversal => inBidirectional(r)
    }

    def inRandomAccess[A](r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        while (__first < __last) {
            __first.++ swap --(__last)
        }
    }

    def inBidirectional[A](r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        while (true) {
            if (__first == __last || __first == --(__last)) {
                return
            } else {
                __first.++ swap __last
            }
        }
    }
}
