
package mada


trait Expr[+R] {
    def eval: R = _eval
    protected def _eval: R
}

case class IdentityExpr[+T1](_1: T1) extends Expr[T1] with Product1[T1] {
    override def _eval = _1
}


object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaIdentityExpr[X](x: X): Expr[X] = IdentityExpr(x)
}
