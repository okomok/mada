

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Sort extends Sort; trait Sort extends Predefs {
    class MadaRngStlSort[A](_1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) {
        def stl_sort = SortExpr(_1, _2).expr
    }
    implicit def toMadaRngStlSort[A <% Ordered[A]](_1: Expr.Of[Rng[A]]): MadaRngStlSort[A] = new MadaRngStlSort[A](_1, _ < _)
    class MadaRngStlSortWith[A](_1: Expr.Of[Rng[A]]) {
        def stl_sortWith(_2: (A, A) => Boolean) = SortExpr(_1, _2).expr
    }
    implicit def toMadaRngStlSortWith[A](_1: Expr.Of[Rng[A]]): MadaRngStlSortWith[A] = new MadaRngStlSortWith[A](_1)
}


case class SortExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = SortImpl(_1.eval, _2)
}


object SortImpl {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        detail.IntroSort(r, f)
    }
}
