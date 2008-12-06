
package mada


// Expr

object Expr {
    def apply[A](e: => A) = new ConstantExpr(e).expr
}

trait Expr[A] {
    protected def _eval: A = { throw new UnknownContextException(this, DefaultContext) }
    protected def _eval[B](c: Context[A, B]): B = c(this)

    final def eval: A = eval(DefaultContext)
    final def eval[B](c: Context[A, B]): B = _eval(c)

    case object DefaultContext extends Context[A, A] {
        override def apply(x: Expr[A]) = x._eval
    }

    final def expr = this
    final def cut = CutExpr(this).expr
    final def xlazy = LazyExpr(this).expr

    final def ! = eval
    final def ![B](c: Context[A, B]) = eval(c)
}


// ExprAdapter

trait ExprAdapter[A] extends Expr[A] {
    protected def _base: Expr[A]
    final def base = _base
    override protected def _eval[B](c: Context[A, B]) = base.eval(c)
}


// Context

trait Context[A, B] extends (Expr[A] => B)


// predefined expressions

class ConstantExpr[A](e: => A) extends Expr[A] {
    override protected def _eval = e
}

case class CutExpr[A](_1: Expr[A]) extends Expr[A] {
    override protected def _eval[B](c: Context[A, B]) = _1.eval(c)
}

case class LazyExpr[A](_1: Expr[A]) extends Expr[A] {
    private val c = new LazyContext(_1.DefaultContext) // DefaultContext only
    override protected def _eval = _1.eval(c)
}

// will be rejected.
object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaExpr[A](e: => A): ConstantExpr[A] = new ConstantExpr[A](e)
}


// predefined contexts

case class LazyContext[A, B](c: Context[A, B]) extends Context[A, B] {
    private val e = new LazyRef[B]
    override def apply(x: Expr[A]) = e := c(x)
}


// exceptions

case class UnknownExprException[A, B](expr: Expr[A], context: Context[A, B]) extends UnsupportedOperationException
case class UnknownContextException[A, B](expr: Expr[A], context: Context[A, B]) extends UnsupportedOperationException
