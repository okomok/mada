

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


case class Cut[A](_1: Expr.Of[A]) extends Alias[Nothing, A] {
    override protected def _alias = _1
}
