

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


// head

object Head extends Head; trait Head extends Predefs {
    class MadaRngHead[A](_1: Expr.Of[Rng[A]]) {
        def head = HeadExpr(_1).expr
    }
    implicit def toMadaRngHead[A](_1: Expr.Of[Rng[A]]): MadaRngHead[A] = new MadaRngHead[A](_1)
}

case class HeadExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Alias[Rng[A], A] {
    override protected def _alias = FirstExpr(_1)
}


// tail

object Tail extends Tail; trait Tail extends Predefs {
    class MadaRngTail[A](_1: Expr.Of[Rng[A]]) {
        def tail = TailExpr(_1).expr
    }
    implicit def toMadaRngTail[A](_1: Expr.Of[Rng[A]]): MadaRngTail[A] = new MadaRngTail[A](_1)
}

case class TailExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = DropExpr(_1, 1L)
}
