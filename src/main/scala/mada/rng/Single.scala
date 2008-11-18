
package mada.rng


// Single

object Single {
    def apply[A](_1: => A) = new Single(_1)
}

class Single[A](a1: => A) {
    def _1 = a1
}


//  Single[A] <-> Expr[Rng[A]]

object SingleConversions extends SingleConversions

trait SingleConversions {
    implicit def toMadaSingleRngExpr[A](from: => Single[A]) = FromSingleExpr(Expr(from)).expr
}


// toRng

object SingleToRng

trait SingleToRng extends Predefs {
    class MadaRngSingleToRng[A](_1: Expr[Single[A]]) {
        def toRng = FromSingleExpr(_1).expr
    }
    implicit def toMadaRngSingleToRng[A](_1: Expr[Single[A]]) = new MadaRngSingleToRng(_1)
}

case class FromSingleExpr[A](_1: Expr[Single[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]): U = c match {
        case DefaultContext => _1 match {
            case ToSingleExpr(x1) => x1.eval
            case _ => forward.eval
        }
        case _ => forward.eval(c)
    }

    private def forward = {
        val ia = new SingleIndexAccess(_1.eval._1).indexAccess
        IndexAccessRngExpr(Expr(ia))
    }
}

class SingleIndexAccess[A](val base: A) extends IndexAccess[A] {
    override def _get(i: Long) = { Assert("out of SingleRng", i == 0); base }
    override def _size = 1
}


// toSingle

object ToSingle extends ToSingle

trait ToSingle extends Predefs {
    class MadaRngToSingle[A](_1: Expr[Rng[A]]) {
        def toSingle = ToSingleExpr(_1).expr
    }
    implicit def toMadaRngToSingle[A](_1: Expr[Rng[A]]) = new MadaRngToSingle(_1)
}

case class ToSingleExpr[A](_1: Expr[Rng[A]]) extends Expr[Single[A]] {
    override def _eval = _1 match {
        case FromSingleExpr(x1) => x1.eval
        case _ => Single(FirstExpr(_1).eval)
    }
}
