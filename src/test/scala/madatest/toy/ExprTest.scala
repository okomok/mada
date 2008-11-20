

package madatest.toy


import junit.framework.Assert._
import mada.{Expr, Context}
import mada.ExprConversions._


trait Rng[A] {
    def size: Long = 999
}


trait Map {
    class MapOperator1[A](expr: Expr[Rng[A]]) {
        def map[B](function: A => B) = MapExpr(expr, function)
    }
    implicit def toMapOperator[A](expr: Expr[Rng[A]]) = new MapOperator1(expr)
}

object Map extends Map
object AmbiguityCheck extends Map


abstract case class RngContext[A] extends Context[Rng[A], Rng[A]] // Context[A, A] doesn't conform to Context[Rng[A], X]
abstract case class DefaultContext_[A] extends Context[A, A]


case class MapExpr[Z, A](val base: Expr[Rng[Z]], val function: Z => A) extends Expr[Rng[A]] {
    var optimized = false
    override def _eval = base match {
        case MapExpr(b, f) => {
            optimized = true
            new MapRng(b.eval, function compose f) // MapRng[Any, To]
        }
        case _ => {
            new MapRng(base.eval, function)
        }
    }
    override def _eval[X](c: Context[Rng[A], X]): X = c match {
        case DefaultContext => _eval
//        case _: DefaultContext_[Rng[_]] => _eval // hmm, ugly. and warnings
        case RngContext() => new MapRng(base.eval, function)
        case SizeContext() => 9 // Optimize
        case _ => c(this) // default
    }
}

class MapRng[Z, A](val base: Rng[Z], val function: Z => A) extends Rng[A] {
}


case class SizeContext[A]() extends Context[Rng[A], Long] {
    override def apply(e: Expr[Rng[A]]) = e.eval.size // default
}

class SizeExpr[A](val base: Expr[Rng[A]]) extends Expr[Long] {
    override def _eval = base.eval(new SizeContext[A])
}


class ExprTest {
    implicit def fromString(s: String) = new Rng[Char] { }

    def testTrivial {
        val e1 = MapExpr(Expr(fromString("abc")), {(x: Char) => 99})
        val e2 = MapExpr(e1, {(x: Int) => 'a'})
        assertFalse(e2.optimized)
        val r = e2.eval
        assertTrue(e2.optimized)
        r match {
            case r: MapRng[_, _] => assertEquals(r.function.asInstanceOf[Char => Char]('X'), 'a')
            case _ => fail("oops")
        }
    }

    def testInfix {
        import Map._
        val e1 = Expr(fromString("abc")).map({(x: Char) => 99})
        val e2 = e1.map({(x: Int) => 'a'})
        assertFalse(e2.optimized)
        val r = e2.eval
        assertTrue(e2.optimized)
        r match {
            case r: MapRng[_, _] => assertEquals(r.function.asInstanceOf[Char => Char]('X'), 'a')
            case _ => fail("oops")
        }
    }

    def testContext {
        import Map._
        val e = Expr(fromString("abc")).map({(x: Char) => 99})
        assertEquals(new SizeExpr(e).eval, 9L)
        assertEquals(new SizeExpr(Expr(fromString("abc"))).eval, 999L)
    }
}

