

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Find._


object Forall extends Forall; trait Forall extends Predefs {
    class MadaRngForall[A](_1: Expr.Of[Rng[A]]) {
        def forall(_2: A => Boolean) = ForallExpr(_1, _2).expr
    }
    implicit def toMadaRngForall[A](_1: Expr.Of[Rng[A]]): MadaRngForall[A] = new MadaRngForall[A](_1)
}


case class ForallExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = _1.find(!_2(_: A)).eval == None
}
