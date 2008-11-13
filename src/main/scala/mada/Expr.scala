
package mada


object Expr {
    def apply[A](e: => A) = new Expr[A] {
        def eval = e
    }
}

trait Expr[A] {
    def eval: A
    def eval[B](c: Context[A, B]): B = c(this)
    final def expr: Expr[A] = this
}


trait Context[A, B] extends (Expr[A] => B)


// seems useless cuz "def eval" is shorter.
class Expression[A](e: => A) extends Expr[A] {
    def eval = e
}
object Expression {
    def apply[A](e: => A) = new Expression(e).expr
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


// seems useless
object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaTerminal[A](base: A) = Terminal(base)
}
