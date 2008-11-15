

package madatest.exprtoy2


import junit.framework.Assert._

/*
trait Expr[T] {
    override def _eval[U](c: Context[T, U]): U
    def defaultContext: Context[T, T]
    final def eval: T = eval(defaultContext)
}

trait Context[T, U] extends (Expr[T] => U)


class ErrorUnknownContext extends UnsupportedOperationException


case class RngContext[A] extends Context[Rng[A], Rng[A]] {
    def apply(x: Expr[Rng[A]]) = { throw new ErrorUnknownContext }
}


trait Rng[A] {
    def size: Long = 999
}


case class MapExpr[Z, A](val base: Expr[Rng[Z]], val function: Z => A) extends Expr[Rng[A]] {

    override def defaultContext = new RngContext[A]

    override def _eval[U](c: Context[Rng[A], U]) = c match {
        case RngContext() => evalRng
        case SizeContext() => 9 // Optimize
        case _ => c(this) // default
    }

    var optimized = false
    private def evalRng: Rng[A] = base match {
        case MapExpr(b, f) => {
            optimized = true
            new MapRng(b.eval(????), function compose f) // MapRng[Any, To]
        }
        case _ => {
            new MapRng(base.eval(new RngContext[Z]), function)
        }
    }

}

class MapRng[Z, A](val base: Rng[Z], val function: Z => A) extends Rng[A] {
}


case class SizeContext[A] extends Context[Long] {
    override def apply(e: Expr[A]) = e.eval(new RngContext[A]).size
}

class SizeExpr[A](val _1: Expr[Rng[A]]) extends Expr {
    override def _eval[U](c: Context[U]) = _1.eval(new SizeContext[A])
}


class ExprTest {
    implicit def fromString(s: String) = new Rng[Char] { }

    def testContext {
        assertEquals(new SizeExpr(e).eval, 9L)
        assertEquals(new SizeExpr(Expr(fromString("abc"))).eval, 999L)
    }
}
*/
