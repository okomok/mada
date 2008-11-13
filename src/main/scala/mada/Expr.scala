
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

case class Terminal[A](base: A) extends Expr[A] {
    def eval = base
}


object ToExpr extends ToExpr

trait ToExpr {
    class MadaToExpr[A](base: A) {
        def toExpr = Terminal(base).expr
    }
    implicit def toMadaToExpr[A](base: A) = new MadaToExpr(base)
}


object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaExpr[A](e: => A) = Expr(e)
}
