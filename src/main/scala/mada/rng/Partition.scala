

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Filter._


object Partition extends Partition; trait Partition extends Predefs {
    class MadaRngPartition[A](_1: Expr.Of[Rng[A]]) {
        def partition(_2: A => Boolean) = PartitionExpr(_1, _2).expr
    }
    implicit def toMadaRngPartition[A](_1: Expr.Of[Rng[A]]): MadaRngPartition[A] = new MadaRngPartition[A](_1)
}


case class PartitionExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], (Rng[A], Rng[A])] {
    override protected def _default = PartitionImpl(_1.eval, _2)
}


object PartitionImpl {
    def apply[A](r: Rng[A], f: A => Boolean): (Rng[A], Rng[A]) = {
        r.assertModels(Traversal.Forward)
        (r./.filter(f)./, r./.filter(!f(_))./)
    }
}
