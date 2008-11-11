

package madatest.exprtoy


import junit.framework.Assert._
import mada.{Expr, Terminal}
import mada.ExprConversions._


trait Rng[A]


object Map {
    class MapOperator1[A](expr: Expr[Rng[A]]) {
        def map[B](function: A => B) = MapImpl(expr, function)
    }
    implicit def toMapOperator[A](expr: Expr[Rng[A]]) = new MapOperator1(expr)

    class MapOperator2[Z, A](expr: MapExpr[Z, A]) {
        def map[B](function: A => B) = MapImpl(expr, function)
    }
    implicit def toMapOperator[Z, A](expr: MapExpr[Z, A]) = new MapOperator2(expr)
}

object MapImpl {
    var expected = 999
    def apply[A, B](expr: Expr[Rng[A]], function: A => B) = {
        assertEquals(expected, 1)
        new MapExpr(expr, function)
    }

    def apply[Z, A, B](expr: MapExpr[Z, A], function: A => B) = {
        assertEquals(expected, 2)
        new MapExpr(expr.base, function compose expr.function)
    }
}


class MapExpr[Z, A](val base: Expr[Rng[Z]], val function: Z => A) extends Expr[Rng[A]] {
    def _eval = new MapRng(base.eval, function)
}

class MapRng[Z, A](val base: Rng[Z], val function: Z => A) extends Rng[A]



class ExprTest {
    implicit def fromString(s: String) = new Rng[Char] { }

    def testTrivial {
        MapImpl.expected = 1
        val e = MapImpl(fromString("abc"), {(x: Char) => 99})
        MapImpl.expected = 2
        MapImpl(e, {(x: Int) => 'a'})
    }

    def testInfix {
        import Map._
        MapImpl.expected = 1
        val e = Terminal(fromString("abc")).map({(x: Char) => 99})
        MapImpl.expected = 2
        e.map({(x: Int) => 'a'})
    }
}

