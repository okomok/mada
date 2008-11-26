
package mada.rng


object Permutation extends Permutation

trait Permutation extends Predefs {
    class MadaRngPermutation[A](_1: Expr[Rng[A]]) {
        def rng_permutation(_2: Expr[Rng[Long]]) = PermutationExpr(_1, _2).expr
    }
    implicit def toMadaRngPermutation[A](_1: Expr[Rng[A]]): MadaRngPermutation[A] = new MadaRngPermutation[A](_1)
}


case class PermutationExpr[A](_1: Expr[Rng[A]], _2: Expr[Rng[Long]]) extends Expr[Rng[A]] {
    override def _eval = PermutationImpl(_1.eval, _2.eval)
}


object PermutationImpl {
    def apply[A](elements: Rng[A], indices: Rng[Long]): Rng[A] = {
        AssertModels(elements, RandomAccessTraversal)
        val (p, q) = (indices.begin, indices.end)
        val pe = elements.begin
        new PermutationPointer(p, pe) <=< new PermutationPointer(q, pe)
    }
}

class PermutationPointer[A](override val _base: Pointer[Long], val elementsBegin: Pointer[A])
        extends PointerAdapter[Long, A, PermutationPointer[A]] {
    override def _read = *(elementsBegin + *(base))
    override def _write(e: A) { *(elementsBegin + *(base)) = e }
    override def _copy = new PermutationPointer(base.copy, elementsBegin)
}
