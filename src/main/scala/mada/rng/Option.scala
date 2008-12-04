
package mada.rng


import Pointer._


//  Option[A] <-> Expr[Rng[A]]

object OptionCompatible extends OptionCompatible; trait OptionCompatible {
    implicit def toMadaOptionRngExpr[A](from: Option[A]): Expr[Rng[A]] = FromOptionExpr(Expr(from)).expr
    implicit def fromMadaOptionRngExpr[A](from: Expr[Rng[A]]): Option[A] = ToOptionExpr(from).eval
}


// toRng

object OptionToRng extends OptionToRng; trait OptionToRng extends Predefs {
    class MadaRngOptionToRng[A](_1: Expr[Option[A]]) {
        def toRng = FromOptionExpr(_1).expr
    }
    implicit def toMadaRngOptionToRng[A](_1: Expr[Option[A]]): MadaRngOptionToRng[A] = new MadaRngOptionToRng[A](_1)
}

case class FromOptionExpr[A](_1: Expr[Option[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]): U = c match {
        case DefaultContext => _1 match {
            case ToOptionExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(c)
    }

    private def delegate = IndexAccessRngExpr(new OptionIndexAccess(_1.eval))
}

class OptionIndexAccess[A](val base: Option[A]) extends IndexAccess[A] {
    override def _get(i: Long) = { Assert("out of OptionRng", i == 0); base.get }
    override def _size = if (base.isEmpty) 0 else 1
}


// toOption

object ToOption extends ToOption; trait ToOption extends Predefs {
    class MadaRngToOption[A](_1: Expr[Rng[A]]) {
        def rng_toOption = ToOptionExpr(_1).expr
    }
    implicit def toMadaRngToOption[A](_1: Expr[Rng[A]]): MadaRngToOption[A] = new MadaRngToOption[A](_1)
}

case class ToOptionExpr[A](_1: Expr[Rng[A]]) extends Expr[Option[A]] {
    override def _eval = _1 match {
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
