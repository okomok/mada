
package mada


object Expr {
    def apply[A](e: => A) = new Expression(e).expr
}

trait Expr[A] {
    def eval: A
    def eval[B](c: Context[A, B]): B = c(this)
    final def apply() = eval
    final def expr: Expr[A] = this
    final def toLazy[A] = LazyExpr(this).expr
}

trait Context[A, B] extends (Expr[A] => B)


case class LazyExpr[A](_1: Expr[A]) extends Expr[A] {
    override lazy val eval = _1.eval
}


class Expression[A](e: => A) extends Expr[A] {
    def eval = e
}


object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaExpr[A](e: => A) = Expr(e)
}