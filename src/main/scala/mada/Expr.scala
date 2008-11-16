
package mada


// Expr

object Expr {
    def apply[A](e: => A) = new ConstantExpr(e).expr
}

trait Expr[A] {
    def _eval: A = { throw new ErrorUnknownContext(this, new DefaultContext[A]) }
    def _eval[B](c: Context[A, B]): B = c(this)

    final def eval: A = eval(new DefaultContext[A])
    final def eval[B](c: Context[A, B]): B = _eval(c)

    final def expr: Expr[A] = this
    final def toLazy[A] = LazyExpr(this).expr
}


// Context

trait Context[A, B] extends (Expr[A] => B)


// predefined expressions

class ConstantExpr[A](e: => A) extends Expr[A] {
    override def _eval = e
}

case class LazyExpr[A](_1: Expr[A]) extends Expr[A] {
    override def _eval[B](c: Context[A, B]) = _1.eval(new LazyContext(c))
}

case class ForwardExpr[A](_1: Expr[A]) extends Expr[A] {
    override def _eval[B](c: Context[A, B]) = _1.eval(c)
}

object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaExpr[A](e: => A) = Expr(e)
}


// predefined contexts

case class DefaultContext[A] extends Context[A, A] {
    override def apply(x: Expr[A]) = x._eval
}

case class LazyContext[A, B](c: Context[A, B]) extends Context[A, B] {
    override def apply(x: Expr[A]) = { lazy val v = c(x); v }
}


// exceptions

case class ErrorUnknownExpr[A, B](expr: Expr[A], context: Context[A, B]) extends UnsupportedOperationException
case class ErrorUnknownContext[A, B](expr: Expr[A], context: Context[A, B]) extends UnsupportedOperationException