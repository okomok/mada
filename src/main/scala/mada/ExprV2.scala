
package mada


object ExprV2 {
    type Of[A] = ExprV2[_, A]

    type Terminal[A] = ExprV2[A, A]

    trait ConstantOf[A] extends Terminal[A] {
        protected def _of: A
        override protected def _eval[B](x: ExprV2[A, B]): B = x match {
            case Self => _of
            case _ => unknown(x)
        }
    }

    trait Method[Z, A] extends ExprV2[Z, A] {
        protected def _1: Of[Z]
        protected def _default: A
        override protected def _eval[B](x: ExprV2[A, B]): B = x match {
            case Self => _1.eval(this)
            case Default => _default
            case _ => unknown(x)
        }
    }

    type Transform[A] = Method[A, A]

    trait Adapter[A] extends Terminal[A] {
        protected def _base: Of[A]
        override protected def _eval[B](x: ExprV2[A, B]): B = x match {
            case Self => _base.eval
            case _ => _base.eval(x)
        }
    }

    /*

    trait MethodAdapter[A, B] extends ExprV2[A, B] {
        protected def _1: Terminal[A]
        protected def _base: ExprV2[A, B]
        protected def _default: B = _base.eval
        override protected def _eval[C](x: ExprV2[B, C]): C = x match {
            case Self => _1.eval(this) // as method
            case Default => _default
            case _ => _base.eval(x)
        }
    }

    */

    case class Constant[A](_1: A) extends ConstantOf[A] {
        override protected val _of = _1
    }

    case class Cut[A](_1: ExprV2.Of[A]) extends Terminal[A] {
        override protected def _eval[B](x: ExprV2[A, B]): B = _1.eval(x)
    }

    case class Lazy[A](_1: ExprV2.Of[A]) extends Terminal[A] {
        private val e = new LazyRef[A]
        override protected def _eval[B](x: ExprV2[A, B]): B = x match {
            case Self => e := _1.eval // Self only
            case _ => unknown(x)
        }
    }


    case object NoSelfCaseError extends Error
    case object NoDefaultCaseError extends Error
}


trait ExprV2[Z, A] {
    protected def _eval[B](x: ExprV2[A, B]): B
    final def eval[B](x: ExprV2[A, B]): B = _eval(x)
    final def eval: A = eval(Self)

    case object Self extends ExprV2.Terminal[A] {
        override protected def _eval[B](x: ExprV2[A, B]): B = throw ExprV2.NoSelfCaseError
    }

    case object Default extends ExprV2.Terminal[A] {
        override protected def _eval[B](x: ExprV2[A, B]): B = throw ExprV2.NoDefaultCaseError
    }

    protected def unknown[B](x: ExprV2[A, B]): B = x.eval(x.Default)

    final def expr = this
    final def cut = ExprV2.Cut(this).expr
    final def xlazy = ExprV2.Lazy(this).expr

    final def ! = eval
    final def ![B](x: ExprV2[A, B]) = eval(x)
}
