

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Append._


object Rotate extends Rotate; trait Rotate extends Predefs {
    class MadaRngRotate[A](_1: Expr.Of[Rng[A]]) {
        def rotate(_2: Rng[A] => Pointer[A]) = RotateExpr(_1, _2).expr
    }
    implicit def toMadaRngRotate[A](_1: Expr.Of[Rng[A]]): MadaRngRotate[A] = new MadaRngRotate[A](_1)
}


case class RotateExpr[A](override val _1: Expr.Of[Rng[A]], _2: Rng[A] => Pointer[A]) extends Expr.Transform[Rng[A]] {
    override protected def _default = RotateImpl(_1.eval, _2)
}


object RotateImpl {
    def apply[A](r: Rng[A], f: Rng[A] => Pointer[A]): Rng[A] = {
        AssertModels(r, Traversal.Forward)
        val mid = f(r)
        (mid <=< r.end)./.append(r.begin <=< mid)./
    }
}
