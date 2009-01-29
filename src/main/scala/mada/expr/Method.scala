

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


trait Method[Z, A] extends Expr[Z, A] {
    protected def _1: Expr.Of[Z]
    protected def _default: A
    override protected def _eval[B](x: Expr[A, B]): B = x match {
        case Self => _1?this
        case Unknown => _default
        case _ => dontKnow(x)
    }
}
