

package madatest.toyexpr2


object Expr {
    type Terminal[A] = Expr[A, A]

    case class Constant[A](_1: A) extends Terminal[A] {
        override protected def _eval[U](x: Expr[A, U]): U = x match {
            case Self => _1
            case _ => unknown(x)
        }
    }
}

/*
    trait Expr[A]
    class Val[A](a: A) extends Expr[A]
    class ApplyImpl[A, B](x: Expr[B => A], y: Expr[B]) extends Expr[A]
    type Apply[A] = ApplyImpl[A, _]

//  data Expr a = Val a | forall b . Apply (Expr (b -> a)) (Expr b)
*/

trait Expr[A, B] {
    protected def _eval[C](x: Expr[B, C]): C
    final def eval[C](c: Expr[B, C]): C = _eval(c)
    final def eval: B = eval(Self)

    case object Self extends Expr[B, B] {
        override protected def _eval[C](x: Expr[B, C]): C = throw new Error("Self case missing")
    }

    case object Default extends Expr[B, B] {
        override protected def _eval[C](x: Expr[B, C]): C = throw new Error("Default case missing")
    }

    protected def unknown[C](x: Expr[B, C]): C = x.eval(x.Default)
}


case class SizeExpr[A](_1: Expr.Terminal[Array[A]]) extends Expr[Array[A], Int] {
    override protected def _eval[U](x: Expr[Int, U]): U = x match {
        case Self => _1.eval(this)
        case Default => _1.eval.size // _1.eval.length will crash compiler!! :-(
        case _ => unknown(x)
    }
}

case class ArrayExpr[A](_1: Array[A]) extends Expr.Terminal[Array[A]] {
    override protected def _eval[U](x: Expr[Array[A], U]): U = x match {
        case SizeExpr(_) => _1.size // optimization
        case Self => _1
        case _ => unknown(x)
    }
}

case class ArrayExpr2[A](_1: Array[A]) extends Expr.Terminal[Array[A]] {
    override protected def _eval[U](x: Expr[Array[A], U]): U = x match {
//        case SizeExpr(_) => _1.length // optimization
        case Self => _1
        case _ => unknown(x)
    }
}


// val id = IdentityExpr1(IdentityExpr2(p))
// id.eval(Self)
// IdentityExpr2(p).eval(id)
// id.eval(id.Default)
//

case class IdentityExpr[A](_1: Expr.Terminal[A]) extends Expr.Terminal[A] {
    override protected def _eval[U](x: Expr[A, U]): U = x match {
        case Self => _1.eval(this)
        case Default => _1 match {
            case i @ IdentityExpr(x1) => i.eval
            case _ => _1.eval
        }
        case _ => unknown(x)
    }
}



class ExprTest {
    def testTrivial {
    }
}

