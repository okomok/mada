

package madatest.toyexpr2


import junit.framework.Assert._

/*
    trait Expr[A]
    class Val[A](a: A) extends Expr[A]
    class ApplyImpl[A, B](x: Expr[B => A], y: Expr[B]) extends Expr[A]
    type Apply[A] = ApplyImpl[A, _]

//  data Expr a = Val a | forall b . Apply (Expr (b -> a)) (Expr b)


*/




object Expr {
    type Of[A] = Expr[_, A]
    type Terminal[A] = Expr[A, A]

    trait Method[Z, A] extends Expr[Z, A] {
        protected def _1: Of[Z] // object
        protected def _default: A
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => _1.eval(this) // as method
            case Default => _default
            case _ => dontKnow(x)
        }
    }

    /*

    trait Adapter[A, B] extends Expr[A, B] {
        protected def _base: Expr[A, B]
        override protected def _eval[C](x: Expr[B, C]): C = _base.eval(x) // delegate
    }

    trait MethodAdapter[A, B] extends Expr[A, B] {
        protected def _1: Terminal[A]
        protected def _base: Expr[A, B]
        protected def _default: B = _base.eval
        override protected def _eval[C](x: Expr[B, C]): C = x match {
            case Self => _1.eval(this) // as method
            case Default => _default
            case _ => _base.eval(x)
        }
    }

    */

    case class Constant[A](_1: A) extends Terminal[A] {
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => _1
            case _ => dontKnow(x)
        }
    }

    case class Cut[A](_1: Expr.Of[A]) extends Terminal[A] {
        override protected def _eval[B](x: Expr[A, B]): B = _1.eval(x)
    }

    case class Lazy[A](_1: Expr.Of[A]) extends Terminal[A] {
        private val e = new mada.LazyRef[A]
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => e := _1.eval(x) // Self only
            case _ => dontKnow(x)
        }
    }


    case object NoSelfCaseError extends Error
    case object NoDefaultCaseError extends Error
}

trait Expr[Z, A] {
    protected def _eval[B](x: Expr[A, B]): B
    final def eval[B](x: Expr[A, B]): B = _eval(x)
    final def eval: A = eval(Self)

    case object Self extends Expr.Terminal[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoSelfCaseError
    }

    case object Default extends Expr.Terminal[A] { // if Self doesn't match
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoDefaultCaseError
    }

    protected def dontKnow[B](x: Expr[A, B]): B = x.eval(x.Default)

    final def expr = this
    final def cut = Expr.Cut(this).expr
    final def lazy_ = Expr.Lazy(this).expr
}


case class SizeExpr[A](override val _1: Expr.Of[List[A]]) extends Expr.Method[List[A], Int] {
    override protected def _default = _1.eval.size
}


case class MapExpr[A, B](override val _1: Expr.Of[List[A]], _2: A => B) extends Expr.Method[List[A], List[B]] {
    override def _default = _1 match {
        case MapExpr(x1, x2) => MapExpr(x1, _2 compose x2).eval
        case _ => _1.eval.map(_2)
    }
}


case class IteratorToListExpr[A](_1: Expr.Of[Iterator[A]]) extends Expr[Iterator[A], List[A]] {
    override protected def _eval[B](x: Expr[List[A], B]): B = x match {
        case Self => _1.eval(this) // as method
        case Default => _1.eval.toList // default-implementation of this method
        case SizeExpr(_) if (hookSize) => 99 // as object
        case MapExpr(x1, x2) => _1.eval.map(x2).toList // as object
        case _ => dontKnow(x)
    }

    var hookSize = false
}/*
*/

// val id = IdentityExpr1(IdentityExpr2(p))
// id.eval
// id.eval(Self)
// IdentityExpr2(p).eval(id) // <= Self
// p.eval(id) <= _
// p.dontKnow(id)
// id.eval(id.Default)
//

// id.eval(aMethod)
// IdentityExpr2(p).eval(aMethod) // <= _

/*
case class IdentityExpr[A](_1: Expr.Terminal[A]) extends Expr.Terminal[A] {
    override protected def _eval[U](x: Expr[A, U]): U = x match {
        case Self => _1.eval(this)
        case Default => _1 match {
            case i @ IdentityExpr(x1) => i.eval
            case _ => _1.eval // delegate
        }
        case _ => _1.eval(x) // delegate
    }
}*/


class ExprTest {
    def aList = Array(1,2,3,4,5).toList
    def anIterator = aList.elements

    def testConstant: Unit = {
        assertEquals(10, Expr.Constant(10).eval)
    }

    def testMethod: Unit = {
        assertEquals(5, SizeExpr(Expr.Constant(aList)).eval)
    }

    def testMap: Unit = {
        val lst = MapExpr(MapExpr(Expr.Constant(aList), { (e: Int) => "wow" }), { (e: String) => 10 }).eval
        assertEquals(10, lst.first)
    }

    def testNontrivial: Unit = {
        val x = IteratorToListExpr(Expr.Constant(anIterator))
        assertEquals(aList, x.eval)
        x.hookSize = true
        assertEquals(99, SizeExpr(x).eval)
        x.hookSize = false
        assertEquals(5, SizeExpr(x).eval)
    }

    def testTrivial {
        val a = Array(1,2,3)
        // SizeExpr(MapExpr(Expr.Constant(a.toList), { (e: Int) => "abc" }))
        ()
    }
}

