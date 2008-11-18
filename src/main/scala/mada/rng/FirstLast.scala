
package mada.rng


// first

object First extends First

trait First extends Predefs {
    class MadaRngFirst[A](_1: Expr[Rng[A]]) {
        def first = FirstExpr(_1).expr
    }
    implicit def toMadaRngFirst[A](_1: Expr[Rng[A]]): MadaRngFirst[A] = new MadaRngFirst[A](_1)
}

case class FirstExpr[A](_1: Expr[Rng[A]]) extends Expr[A] {
    override def _eval = FirstImpl(_1.eval)
}

object FirstImpl {
    def apply[A](r: Rng[A]): A = {
        Assert("out of Rng", !IsEmptyImpl(r))
        *(r.begin)
    }
}


// last

object Last extends Last

trait Last extends Predefs {
    class MadaRngLast[A](_1: Expr[Rng[A]]) {
        def last = LastExpr(_1).expr
    }
    implicit def toMadaRngLast[A](_1: Expr[Rng[A]]): MadaRngLast[A] = new MadaRngLast[A](_1)
}

case class LastExpr[A](_1: Expr[Rng[A]]) extends Expr[A] {
    override def _eval = LastImpl(_1.eval)
}

object LastImpl {
    def apply[A](r: Rng[A]): A = {
        AssertModels(r, BidirectionalTraversal)
        Assert("out of Rng", !IsEmptyImpl(r))
        *(--(r.end))
    }
}
