

package madatest.toyexpr2

/*
    trait Expr[A]
    class Val[A](a: A) extends Expr[A]
    class ApplyImpl[A, B](x: Expr[B => A], y: Expr[B]) extends Expr[A]
    type Apply[A] = ApplyImpl[A, _]

//  data Expr a = Val a | forall b . Apply (Expr (b -> a)) (Expr b)
*/


object Expr {
    type Terminal[A] = Expr[A, A]

    /*
    trait Object[A] extends Expr.Terminal[A] {
        protected def _1: Terminal[A]
        protected def _method[U](x: Expr[A, U]: U
        override protected def _eval[U](x: Expr[Array[A], U]): U = x match {
            case Self => _1
            case _ => _method(x)
        }
    }
    */

    trait Method[A, B] extends Expr[A, B] {
        protected def _1: Terminal[A] // object
        protected def _default: B
        override protected def _eval[C](x: Expr[B, C]): C = x match {
            case Self => _1.eval(this) // as method
            case Default => _default
            case _ => unknown(x)
        }
    }

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

    case class Constant[A](_1: A) extends Terminal[A] {
        override protected def _eval[U](x: Expr[A, U]): U = x match {
            case Self => _1
            case _ => unknown(x)
        }
    }
}

trait Expr[A, B] {
    protected def _eval[C](x: Expr[B, C]): C
    final def eval[C](c: Expr[B, C]): C = _eval(c)
    final def eval: B = eval(Self)

    case object Self extends Expr[B, B] {
        override protected def _eval[C](x: Expr[B, C]): C = throw new Error("Self case missing")
    }

    case object Default extends Expr[B, B] { // thrown if Self doesn't match
        override protected def _eval[C](x: Expr[B, C]): C = throw new Error("Default case missing")
    }

    protected def unknown[C](x: Expr[B, C]): C = x.eval(x.Default)
}


/*
case class SizeExpr[A](_1: Expr.Terminal[Array[A]]) extends Expr[Array[A], Int] {
    override protected def _eval[U](x: Expr[Int, U]): U = x match {
        case Self => _1.eval(this)
        case Default => _1.eval.size // .length will crash compiler!! :-(
        case _ => unknown(x)
    }
}
*/
case class SizeExpr[A](override val _1: Expr.Terminal[List[A]]) extends Expr.Method[List[A], Int] {
    override def _default = _1.eval.size
}

/*
case class ArrayExpr2[A](_1: Array[A]) extends Expr.Terminal[Array[A]] {
    override protected def _eval[U](x: Expr[Array[A], U]): U = x match {
        case SizeExpr(_) => _1.length // optimization
        case Self => _1
        case _ => unknown(x)
    }
}
case class ArrayExpr[A](override val _1: Expr.Terminal[Array[A]]) extends Expr.Object[Array[A]] {
    override protected def _method[U](x: Expr[Array[A], U]): U = x match {
        case SizeExpr(_) => _1.size // optimization
        case _ => unknown(x)
    }
}
*/


case class Map[A, B](override val _1: Expr.Terminal[List[A]], _2: A => B) extends Expr.Method[List[A], List[B]] {
    override def _default = _1 match {
        case Map(x1, x2) => Map(x1, _2 compose x2).eval
        case _ => _1.eval.map(_2)
    }
}


// SizeExpr(IteratorToListExpr(p))

case class IteratorToListExpr[A](_1: Expr.Terminal[Iterator[A]]) extends Expr[Iterator[A], List[A]] {
    override protected def _eval[U](x: Expr[List[A], U]): U = x match {
        case Self => _1.eval(this) // as method
        case Default => _1.eval.toList // default-implementation of this method
        case SizeExpr(_) => 99 // as object
        case Map(x1, x2) => _1.eval.map(x2).toList // as object
        case _ => unknown(x)
    }
}

// val id = IdentityExpr1(IdentityExpr2(p))
// id.eval
// id.eval(Self)
// IdentityExpr2(p).eval(id) // <= Self
// p.eval(id) <= _
// p.unknown(id)
// id.eval(id.Default)
//

// id.eval(aMethod)
// IdentityExpr2(p).eval(aMethod) // <= _

case class IdentityExpr[A](_1: Expr.Terminal[A]) extends Expr.Terminal[A] {
    override protected def _eval[U](x: Expr[A, U]): U = x match {
        case Self => _1.eval(this)
        case Default => _1 match {
            case i @ IdentityExpr(x1) => i.eval
            case _ => _1.eval // delegate
        }
        case _ => _1.eval(x) // delegate
    }
}



class ExprTest {
    def testTrivial {
    }
}

