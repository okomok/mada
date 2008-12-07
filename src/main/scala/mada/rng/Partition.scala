
package mada.rng


import Filter._


object Partition extends Partition; trait Partition extends Predefs {
    class MadaRngPartition[A](_1: ExprV2.Of[Rng[A]]) {
        def partition(_2: A => Boolean) = PartitionExpr(_1, _2).expr
    }
    implicit def toMadaRngPartition[A](_1: ExprV2.Of[Rng[A]]): MadaRngPartition[A] = new MadaRngPartition[A](_1)
}


case class PartitionExpr[A](override val _1: ExprV2.Of[Rng[A]], _2: A => Boolean) extends ExprV2.Method[Rng[A], (Rng[A], Rng[A])] {
    override def _default = PartitionImpl(_1.eval, _2)
}


object PartitionImpl {
    def apply[A](r: Rng[A], f: A => Boolean): (Rng[A], Rng[A]) = {
        AssertModels(r, ForwardTraversal)
        (r.toExpr.filter(f).eval, r.toExpr.filter(!f(_)).eval)
    }
}
