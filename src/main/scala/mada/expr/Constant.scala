

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


trait ConstantOf[A] extends Expr.Terminal[A] {
    protected def _of: A
    override protected def _eval[B](x: Expr[A, B]): B = x match {
        case Self => _of
        case _ => dontKnow(x)
    }
}

case class Constant[A](_1: A) extends ConstantOf[A] {
    override protected val _of = _1
}
