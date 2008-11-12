

package madatest.exprtoy


import junit.framework.Assert._
import mada.{Expr, Terminal, Context}
import mada.ExprConversions._


trait Rng[A] {
    def size: Long = 999
}


trait Map {
    class MapOperator1[A](expr: Expr[Rng[A]]) {
        def map[B](function: A => B) = MapImpl(expr, function)
    }
    implicit def toMapOperator[A](expr: Expr[Rng[A]]) = new MapOperator1(expr)
}

object Map extends Map
object AmbiguityCheck extends Map

trait MapOpt extends Map {
    class MapOperator2[Z, A](expr: MapExpr[Z, A]) {
        def map[B](function: A => B) = MapImpl(expr, function)
    }
    implicit def toMapOperator[Z, A](expr: MapExpr[Z, A]) = new MapOperator2(expr)
}
object MapOpt extends MapOpt


object MapImpl {
    var testMe = true
    var expected = 999
    def apply[A, B](expr: Expr[Rng[A]], function: A => B) = {
        if (testMe)
            assertEquals(expected, 1)
        new MapExpr(expr, function)
    }

    def apply[Z, A, B](expr: MapExpr[Z, A], function: A => B) = {
        if (testMe)
            assertEquals(expected, 2)
        new MapExpr(expr.base, function compose expr.function)
    }
}

abstract case class RngContext[A] extends Context[Rng[A], Rng[A]] // Context[A, A] doesn't conform to Context[Rng[A], X]


class MapExpr[Z, A](val base: Expr[Rng[Z]], val function: Z => A) extends Expr[Rng[A]] {
    override def eval = new MapRng(base.eval, function)
    override def eval[X](c: Context[Rng[A], X]): X = c match {
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
    override def eval = base.eval(new SizeContext[A])
}


class ExprTest {
    implicit def fromString(s: String) = new Rng[Char] { }

    def testTrivial {
        MapImpl.expected = 1
        val e = MapImpl(fromString("abc"), {(x: Char) => 99})
        MapImpl.expected = 2
        MapImpl(e, {(x: Int) => 'a'})
    }

    def testInfix {
        MapImpl.testMe = true
        // order dependent!
        import MapOpt._
//       import AmbiguityCheck._
//       import Map._
        MapImpl.expected = 1
        val e = Terminal(fromString("abc")).map({(x: Char) => 99})
        MapImpl.expected = 2
        e.map({(x: Int) => 'a'})
    }

    def testContext {
        import MapOpt._
        MapImpl.testMe = false
        val e = Terminal(fromString("abc")).map({(x: Char) => 99})
        assertEquals(new SizeExpr(e).eval, 9L)
        assertEquals(new SizeExpr(Terminal(fromString("abc"))).eval, 999L)
    }
}

