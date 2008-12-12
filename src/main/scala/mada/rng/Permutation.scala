

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Permutation extends Permutation; trait Permutation extends Predefs {
    class MadaRngPermutation[A](_1: Expr.Of[Rng[A]]) {
        def permutation(_2: Expr.Of[Rng[Long]]) = PermutationExpr(_1, _2).expr
    }
    implicit def toMadaRngPermutation[A](_1: Expr.Of[Rng[A]]): MadaRngPermutation[A] = new MadaRngPermutation[A](_1)
}


case class PermutationExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[Long]])
        extends Expr.Transform[Rng[A]] {
    override protected def _default = PermutationImpl(_1.eval, _2.eval)
}


object PermutationImpl {
    def apply[A](elements: Rng[A], indices: Rng[Long]): Rng[A] = {
        AssertModels(elements, Traversal.RandomAccess)
        val (p, q) = indices.toPair
        val pe = elements.begin
        new PermutationPointer(p, pe) <=< new PermutationPointer(q, pe)
    }
}

class PermutationPointer[A](override protected val _base: Pointer[Long], val elementsBegin: Pointer[A])
        extends PointerAdapter[Long, A, PermutationPointer[A]] {
    override protected def _read = *(elementsBegin + *(base))
    override protected def _write(e: A) = { *(elementsBegin + *(base)) = e }
    override protected def _copy = new PermutationPointer(base.copy, elementsBegin)
}
