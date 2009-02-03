

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


case class Lazy[A](_1: Expr.Of[A]) extends Expr.Terminal[A] {
    private val e = new Proxies.LazyRef[A]
    override protected def _eval[B](x: Expr[A, B]): B = x match {
        case Self => { e := _1.eval; e.self } // Self only
        case _ => dontKnow(x)
    }
}
