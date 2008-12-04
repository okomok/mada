
package mada.rng


import Pointer._


object Step extends Step; trait Step extends Predefs {
    class MadaRngStep[A](_1: Expr[Rng[A]]) {
        def rng_step(_2: Long) = StepExpr(_1, _2).expr
    }
    implicit def toMadaRngStep[A](_1: Expr[Rng[A]]): MadaRngStep[A] = new MadaRngStep[A](_1)
}


case class StepExpr[A](_1: Expr[Rng[A]], _2: Long) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case StepExpr(x1, x2) => StepExpr(x1, x2 + _2).eval // step-step fusion
        case _ => StepWithExpr(_1, StepFunction(_: Rng[A], _2)).eval
    }
}


object StepFunction {
    def apply[A](r: Rng[A], d: Long): Pointer[A] = {
        Assert("zero step isn't allowed", d > 0)
        val (p, q) = r.toPair
        p.traversal match {
            case _: RandomAccessTraversal => p += Math.min(q - p, d)
            case _: SinglePassTraversal => {
                var dd = d
                do {
                    ++(p)
                    dd -= 1
                } while (dd != 0 && p != q)
                p
            }
        }
    }
}
