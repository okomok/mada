
package mada.rng


import First._


// Single (writable Cell)

case class Single[A](var elem: A)


//  Single[A] -> Expr[Rng[A]]

object SingleCompatible extends SingleCompatible; trait SingleCompatible {
    implicit def madaRng_Single2ExprOfRng[A](from: Single[A]): Expr.Of[Rng[A]] = FromSingleExpr(Expr(from)).expr
}


// toRng

object SingleToRng extends SingleToRng; trait SingleToRng extends Predefs {
    class MadaRngSingleToRng[A](_1: Expr.Of[Single[A]]) {
        def toRng = FromSingleExpr(_1).expr
    }
    implicit def toMadaRngSingleToRng[A](_1: Expr.Of[Single[A]]): MadaRngSingleToRng[A] = new MadaRngSingleToRng[A](_1)
}

case class FromSingleExpr[A](_1: Expr.Of[Single[A]]) extends Expr[Single[A], Rng[A]] {
    override protected def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ToSingleExpr(x1) => x1.eval
            case _ => aux.eval
        }
        case _ => aux.eval(x)
    }

    private def aux = IndexAccessRngExpr(new SingleIndexAccess(_1.eval))
}

class SingleIndexAccess[A](val base: Single[A]) extends IndexAccess[A] {
    override def _set(i: Long, e: A) = { assertZero(i); base.elem = e }
    override def _get(i: Long) = { assertZero(i); base.elem }
    override def _size = 1
    private def assertZero(i: Long) = Assert("out of SingleRng", i == 0)
}


// toSingle

object ToSingle extends ToSingle; trait ToSingle extends Predefs {
    class MadaRngToSingle[A](_1: Expr.Of[Rng[A]]) {
        def toSingle = ToSingleExpr(_1).expr
    }
    implicit def toMadaRngToSingle[A](_1: Expr.Of[Rng[A]]): MadaRngToSingle[A] = new MadaRngToSingle[A](_1)
}

case class ToSingleExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Single[A]] {
    override protected def _default = _1 match {
        case FromSingleExpr(x1) => x1.eval
        case _ => Single(_1.first.eval)
    }
}
