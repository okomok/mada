

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object From extends From; trait From extends Namespace
        with Predefs
        with FromRange {
    def from[A](x: Expr.Of[Vector[A]]) = x
}


object __/ extends __/; trait __/ extends Namespace
        with Predefs
        with FromRange {
    object / {
        def from[A](x: Expr.Of[Vector[A]]) = x
    }
}
