

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import CopyTo._
import From._
import jcl.ArrayListToRng._
import jcl.ToArrayList._


// Note: implicit call of force seems not good, because force is a heavy method.


object Sort extends Sort; trait Sort extends Predefs {
    class MadaRngSort[A](_1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) {
        def sort = SortExpr(_1, _2).expr
    }
    implicit def toMadaRngSort[A <% Ordered[A]](_1: Expr.Of[Rng[A]]): MadaRngSort[A] = new MadaRngSort[A](_1, _ < _)
    class MadaRngSortWith[A](_1: Expr.Of[Rng[A]]) {
        def sortWith(_2: (A, A) => Boolean) = SortExpr(_1, _2).expr
    }
    implicit def toMadaRngSortWith[A](_1: Expr.Of[Rng[A]]): MadaRngSortWith[A] = new MadaRngSortWith[A](_1)
}


case class SortExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = SortImpl(_1.eval, _2)
}


object SortImpl {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = {
        AssertModels(r, Traversal.RandomAccess)
//        detail.IntroSort(r, f)
        val a = r./.jcl_toArrayList./
        java.util.Collections.sort(a,
            new java.util.Comparator[A] {
                override def compare(x: A, y: A) = {
                    val less = f(x, y)
                    if (less) -1 else if (!less && !f(y, x)) 0 else 1
                }
            })
        from(a).copyTo(r.begin).eval
    }
}
