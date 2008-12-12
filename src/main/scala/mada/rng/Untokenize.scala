

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Append._
import ReadOnly._


object Untokenize extends Untokenize; trait Untokenize extends Predefs {
    class MadaRngUntokenize[A](_1: Expr.Of[Rng[Rng[A]]]) {
        def untokenize(_2: Expr.Of[Rng[A]]) = UntokenizeExpr(_1, _2, None).expr
        def untokenize(_2: Expr.Of[Rng[A]], _3: Traversal) = UntokenizeExpr(_1, _2, Some(_3)).expr
    }
    implicit def toMadaRngUntokenize[A](_1: Expr.Of[Rng[Rng[A]]]): MadaRngUntokenize[A] = new MadaRngUntokenize[A](_1)
}


case class UntokenizeExpr[A](_1: Expr.Of[Rng[Rng[A]]], _2: Expr.Of[Rng[A]], _3: Option[Traversal]) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = {
        val sep = _2.readOnly.eval
        AssertModels(sep, Traversal.Forward)
        FlattenExpr(MapExpr(_1, { (r: Rng[A]) => sep./.append(r.toExpr)./ }), _3)
    }
}
