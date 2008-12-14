

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


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
        AssertModels(r, Traversal.RandomAccess)
        val (p, q) = r.toPair
        aux(p, 0, q - p, g)
    }

    def aux[A](at: Pointer[A], __first: Long, __last: Long, g: Long => Long): Unit = {
        if (__first == __last) {
            return
        }

        var __i = __first + 1
        while (__i != __last) {
            detail.PointerSwap(at, __i, __first + g((__i - __first) + 1))
            __i += 1
        }
    }
}

class DefaultRandomNumberGenerator extends (Long => Long) {
    private val rnd = new Random
    def apply(__n: Long) = Math.abs(rnd.nextLong) % __n
}
