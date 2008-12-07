
package mada


object Expr {
    type Of[A] = Expr[_, A]

    type Identity[A] = Expr[A, A]

    type Terminal[A] = Expr[Nothing, A]

    trait ConstantOf[A] extends Terminal[A] {
        protected def _of: A
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => _of
            case _ => unknown(x)
        }
    }

    trait Method[Z, A] extends Expr[Z, A] {
        protected def _1: Of[Z]
        protected def _default: A
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => _1.eval(this)
            case Default => _default
            case _ => unknown(x)
        }
    }

    type Transform[A] = Method[A, A]

    trait Alias[Z, A] extends Expr[Z, A] {
        protected def _alias: Of[A]
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => _alias.eval
            case _ => _alias.eval(x)
        }
    }


    case class Constant[A](_1: A) extends ConstantOf[A] {
        override protected val _of = _1
    }

    case class Cut[A](_1: Of[A]) extends Alias[Nothing, A] {
        override protected def _alias = _1
    }

    case class Lazy[A](_1: Of[A]) extends Terminal[A] {
        private val e = new LazyRef[A]
        override protected def _eval[B](x: Expr[A, B]): B = x match {
            case Self => e := _1.eval // Self only
            case _ => unknown(x)
        }
    }


    case object NoSelfCaseError extends Error
    case object NoDefaultCaseError extends Error
}


trait Expr[Z, A] {
    protected def _eval[B](x: Expr[A, B]): B
    final def eval[B](x: Expr[A, B]): B = _eval(x)
    final def eval: A = eval(Self)

    case object Self extends Expr.Identity[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoSelfCaseError
    }

    case object Default extends Expr.Identity[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoDefaultCaseError
    }

    protected def unknown[B](x: Expr[A, B]): B = x.eval(x.Default)

    final def expr = this
    final def cut = Expr.Cut(this).expr
    final def xlazy = Expr.Lazy(this).expr

    final def ! = eval
    final def ![B](x: Expr[A, B]) = eval(x)
}
