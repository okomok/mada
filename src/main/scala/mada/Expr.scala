
package mada


trait Expr[A] {
    def eval: A
    def eval[B](c: Context[A, B]): B = c(this)
}

trait Context[A, B] extends (Expr[A] => B)


case class Terminal[A](base: A) extends Expr[A] {
    def eval = base
}


object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaTerminal[A](base: A) = Terminal(base)
}
