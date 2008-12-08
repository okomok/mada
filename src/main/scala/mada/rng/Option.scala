
package mada.rng


import Pointer._


//  Option[A] <-> Expr[Rng[A]]

object OptionCompatible extends OptionCompatible; trait OptionCompatible {
    implicit def madaRng_Option2ExprRng[A](from: Option[A]): Expr.Of[Rng[A]] = FromOptionExpr(Expr(from)).expr
}


// toRng

object OptionToRng extends OptionToRng; trait OptionToRng extends Predefs {
    class MadaRngOptionToRng[A](_1: Expr.Of[Option[A]]) {
        def toRng = FromOptionExpr(_1).expr
    }
    implicit def toMadaRngOptionToRng[A](_1: Expr.Of[Option[A]]): MadaRngOptionToRng[A] = new MadaRngOptionToRng[A](_1)
}

case class FromOptionExpr[A](_1: Expr.Of[Option[A]]) extends Expr[Option[A], Rng[A]] {
    override protected def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ToOptionExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(x)
    }

    private def delegate = IndexAccessRngExpr(new OptionIndexAccess(_1.eval))
}

class OptionIndexAccess[A](val base: Option[A]) extends IndexAccess[A] {
    override def _get(i: Long) = { Assert("out of OptionRng", i == 0); base.get }
    override def _size = if (base.isEmpty) 0 else 1
}


// toOption

object ToOption extends ToOption; trait ToOption extends Predefs {
    class MadaRngToOption[A](_1: Expr.Of[Rng[A]]) {
        def toOption = ToOptionExpr(_1).expr
    }
    implicit def toMadaRngToOption[A](_1: Expr.Of[Rng[A]]): MadaRngToOption[A] = new MadaRngToOption[A](_1)
}

case class ToOptionExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Option[A]] {
    override protected def _default = _1 match {
        case FromOptionExpr(x1) => x1.eval
        case _ => ToOptionImpl(_1.eval)
    }
}

object ToOptionImpl {
    def apply[A](r: Rng[A]): Option[A] = {
        val (p, q) = r.toPair
        if (p == q) None else Some(*(p))
    }
}
