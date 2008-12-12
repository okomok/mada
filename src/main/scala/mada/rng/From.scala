

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object From extends From; trait From extends Namespace
        with Predefs
        with FromInterval {
    def from[A](x: Expr.Of[Rng[A]]) = x
}
