
package mada.rng


// first

object First extends First

trait First {
    class MadaRngFirst[A](_1: Expr[Rng[A]]) {
        def first = FirstExpr(_1).expr
    }
    implicit def toMadaRngFirst[A](_1: Expr[Rng[A]]) = new MadaRngFirst(_1)
}

case class FirstExpr[A](_1: Expr[Rng[A]]) extends Expr[A] {
    def eval = FirstImpl(_1.eval)
}

object FirstImpl {
    def apply[A](r: Rng[A]): A = {
        Assert("out of Rng", !IsEmptyImpl(r))
        *(r.begin)
    }
}


// last

object Last extends Last

trait Last {
    class MadaRngLast[A](_1: Expr[Rng[A]]) {
        def last = LastExpr(_1).expr
    }
    implicit def toMadaRngLast[A](_1: Expr[Rng[A]]) = new MadaRngLast(_1)
}

case class LastExpr[A](_1: Expr[Rng[A]]) extends Expr[A] {
    def eval = LastImpl(_1.eval)
}

object LastImpl {
    def apply[A](r: Rng[A]): A = {
        Assert("out of Rng", !IsEmptyImpl(r))
        Assert("requires BidirectionalRng", r models BidirectionalTraversal)
        *(--(r.end))
    }
}
