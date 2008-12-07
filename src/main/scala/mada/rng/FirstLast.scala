
package mada.rng


import Pointer._


// first

object First extends First; trait First extends Predefs {
    class MadaRngFirst[A](_1: ExprV2.Of[Rng[A]]) {
        def first = FirstExpr(_1).expr
    }
    implicit def toMadaRngFirst[A](_1: ExprV2.Of[Rng[A]]): MadaRngFirst[A] = new MadaRngFirst[A](_1)
}

case class FirstExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], A] {
    override def _default = FirstImpl(_1.eval)
}

object FirstImpl {
    def apply[A](r: Rng[A]): A = {
        AssertNotEmpty(r)
        *(r.begin)
    }
}


// last

object Last extends Last; trait Last extends Predefs {
    class MadaRngLast[A](_1: ExprV2.Of[Rng[A]]) {
        def last = LastExpr(_1).expr
    }
    implicit def toMadaRngLast[A](_1: ExprV2.Of[Rng[A]]): MadaRngLast[A] = new MadaRngLast[A](_1)
}

case class LastExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], A] {
    override def _default = LastImpl(_1.eval)
}

object LastImpl {
    def apply[A](r: Rng[A]): A = {
        AssertNotEmpty(r)
        r.traversal match {
            case _: BidirectionalTraversal => *(--(r.end))
            // case _: ForwardTraversal => inForward(r)
            case _: SinglePassTraversal => inSinglePass(r)
        }
    }

    private def inForward[A](r: Rng[A]): A = {
        val (p, q) = r.toPair
        var p_ = p.copy
        while (++(p) != q) { p_ = p.copy }
        *(p_)
    }

    private def inSinglePass[A](r: Rng[A]): A = {
        val (p, q) = r.toPair
        var e = *(p)
        while (++(p) != q) { e = *(p) }
        e
    }
}
