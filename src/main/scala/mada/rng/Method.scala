

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Method extends Method; trait Method extends Predefs {
    class MadaRngMethod[A](_1: Expr.Of[Rng[A]]) {
        def method[B](_2: Rng[A] => B) = MethodExpr(_1, _2).expr
        def method[B](_2: Rng[A] => Pointer[B], _3: Rng[A] => Pointer[B]) = MethodExpr(_1, { (r: Rng[A]) => _2(r) <=< _3(r) } ).expr
    }
    implicit def toMadaRngMethod[A](_1: Expr.Of[Rng[A]]): MadaRngMethod[A] = new MadaRngMethod[A](_1)
}


case class MethodExpr[A, B](override val _1: Expr.Of[Rng[A]], _2: Rng[A] => B) extends Expr.Method[Rng[A], B] {
    override protected def _default = _2(_1.eval)
}
