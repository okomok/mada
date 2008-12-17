

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Sort extends Sort; trait Sort extends Predefs {
    class MadaRngStlSort[A](_1: Expr.Of[Rng[A]]) {
        def stl_sort(implicit _2: A => Ordered[A]) = SortExpr(_1, _2).expr
        def stl_sortWith(_2: (A, A) => Boolean) = SortWithExpr(_1, _2).expr
    }
    implicit def toMadaRngStlSort[A](_1: Expr.Of[Rng[A]]): MadaRngStlSort[A] = new MadaRngStlSort[A](_1)
}


case class SortExpr[A](_1: Expr.Of[Rng[A]], _2: A => Ordered[A]) extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = SortWithExpr[A](_1, _2(_) < _)
}

case class SortWithExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = SortImpl(_1.eval, _2)
}


object SortImpl {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        detail.IntroSort(r, f)
    }
}
