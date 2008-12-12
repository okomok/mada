

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Find._


object Exists extends Exists; trait Exists extends Predefs {
    class MadaRngExists[A](_1: Expr.Of[Rng[A]]) {
        def exists(_2: A => Boolean) = ExistsExpr(_1, _2).expr
    }
    implicit def toMadaRngExists[A](_1: Expr.Of[Rng[A]]): MadaRngExists[A] = new MadaRngExists[A](_1)
}


case class ExistsExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = _1.find(_2).eval != None
}
