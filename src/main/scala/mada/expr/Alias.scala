

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


trait Alias[Z, A] extends Expr[Z, A] {
    protected def _alias: Expr.Of[A]
    override protected def _eval[B](x: Expr[A, B]): B = x match {
        case Self => _alias.eval
        case _ => _alias.eval(x)
    }
}
