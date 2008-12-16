

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object PartialSort extends PartialSort; trait PartialSort extends Predefs {
    class MadaRngStlPartialSort[A](_1: Expr.Of[Rng[A]]) {
        def stl_partialSort(_2: Rng[A] => Pointer[A])(implicit _3: A => Ordered[A]) = PartialSortExpr[A](_1, _2, _3(_) < _).expr
        def stl_partialSortWith(_2: Rng[A] => Pointer[A], _3: (A, A) => Boolean) = PartialSortExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlPartialSort[A](_1: Expr.Of[Rng[A]]): MadaRngStlPartialSort[A] = new MadaRngStlPartialSort[A](_1)
}


case class PartialSortExpr[A](override val _1: Expr.Of[Rng[A]], _2: Rng[A] => Pointer[A], _3: (A, A) => Boolean)
        extends Expr.Method[Rng[A], Unit] {
    override protected def _default = PartialSortImpl(_1.eval, _2, _3)
}


object PartialSortImpl {
    def apply[A](r: Rng[A], mid: Rng[A] => Pointer[A], __comp: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, mid(r) - __*, __last, __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, __middle: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        MakeHeapImpl(*, __first, __middle, __comp)
        var __i = __middle
        while (__i < __last) {
            if (__comp(*(__i), *(__first))) {
                PopHeapImpl(*, __first, __middle, __i, *(__i), __comp)
            }
            __i += 1
        }
        SortHeapImpl(*, __first, __middle, __comp)
    }
}
