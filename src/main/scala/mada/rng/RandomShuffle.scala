

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


import java.util.Random


object RandomShuffle extends RandomShuffle; trait RandomShuffle extends Predefs {
    class MadaRngRandomShuffle[A](_1: Expr.Of[Rng[A]]) {
        def randomShuffle = RandomShuffleExpr(_1, new DefaultRandomNumberGenerator).expr
        def randomShuffle(_2: Long => Long) = RandomShuffleExpr(_1, _2).expr
    }
    implicit def toMadaRngRandomShuffle[A](_1: Expr.Of[Rng[A]]): MadaRngRandomShuffle[A] = new MadaRngRandomShuffle[A](_1)
}


case class RandomShuffleExpr[A](override val _1: Expr.Of[Rng[A]], _2: Long => Long) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = RandomShuffleImpl(_1.eval, _2)
}


object RandomShuffleImpl {
    def apply[A](r: Rng[A], g: Long => Long): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (p, q) = r.toPair
        aux(p, 0, q - p, g)
    }

    def aux[A](* : Pointer[A], __first: Long, __last: Long, g: Long => Long): Unit = {
        if (__first == __last) {
            return
        }

        var __i = __first + 1
        while (__i != __last) {
            *.swap(__i, __first + g((__i - __first) + 1))
            __i += 1
        }
    }
}

class DefaultRandomNumberGenerator extends (Long => Long) {
    private val rnd = new Random
    def apply(__n: Long) = Math.abs(rnd.nextLong) % __n
}
