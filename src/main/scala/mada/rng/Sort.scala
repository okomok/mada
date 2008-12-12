

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


// Note: implicit call of force seems not good, because force is a heavy method.


object Sort extends Sort; trait Sort extends Predefs {
    class MadaRngSort[A](_1: Expr.Of[Rng[A]]) {
        def sort(_2: (A, A) => Boolean) = SortExpr(_1, _2).expr
    }
    implicit def toMadaRngSort[A](_1: Expr.Of[Rng[A]]): MadaRngSort[A] = new MadaRngSort[A](_1)
}


case class SortExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Rng[A]] {
    override protected def _default = SortImpl(_1.eval, _2)
}


object SortImpl {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Rng[A] = {
        AssertModels(r, Traversal.RandomAccess)
        detail.IntroSort(r, f)
        r
    }
}
