
package mada


trait Expr[A] {
    def eval: A = _eval
    def eval[X](c: Context[A, X]): X = _eval(c)
    protected def _eval: A
    protected def _eval[X](c: Context[A, X]): X = c(this)
}

trait Context[A, X] extends (Expr[A] => X)

abstract case class DefaultContext[A]() extends Context[A, A]


case class Terminal[T1](_1: T1) extends Expr[T1] with Product1[T1] {
    override def _eval = _1
}



object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaTerminal[X](x: X): Expr[X] = Terminal(x)
}
