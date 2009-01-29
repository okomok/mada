

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


trait Start[X] { self: X =>
    def toExpr = Expr(self)
    def / = toExpr
}
