
package mada.rng


import Pointer._


// first

object First extends First

trait First extends Predefs {
    class MadaRngFirst[A](_1: Expr[Rng[A]]) {
        def rng_first = FirstExpr(_1).expr
    }
    implicit def toMadaRngFirst[A](_1: Expr[Rng[A]]): MadaRngFirst[A] = new MadaRngFirst[A](_1)
}

case class FirstExpr[A](_1: Expr[Rng[A]]) extends Expr[A] {
    override def _eval = FirstImpl(_1.eval)
}

object FirstImpl {
    def apply[A](r: Rng[A]): A = {
        AssertNotEmpty(r)
        *(r.begin)
    }
}


// last

object Last extends Last

trait Last extends Predefs {
    class MadaRngLast[A](_1: Expr[Rng[A]]) {
        def rng_last = LastExpr(_1).expr
    }
    implicit def toMadaRngLast[A](_1: Expr[Rng[A]]): MadaRngLast[A] = new MadaRngLast[A](_1)
}

case class LastExpr[A](_1: Expr[Rng[A]]) extends Expr[A] {
    override def _eval = LastImpl(_1.eval)
}

object LastImpl {
    def apply[A](r: Rng[A]): A = {
        AssertNotEmpty(r)
        r.traversal match {
            case BidirectionalTraversal => *(--(r.end))
            // case ForwardTraversal => inForward(r)
            case SinglePassTraversal => inSinglePass(r)
        }
    }

    private def inForward[A](r: Rng[A]): A = {
        val (p, q) = (r.begin, r.end)
        var p_ = p.copy
        while (++(p) != q) { p_ = p.copy }
        *(p_)
    }

    private def inSinglePass[A](r: Rng[A]): A = {
        val (p, q) = (r.begin, r.end)
        var e = *(p)
        while (++(p) != q) { e = *(p) }
        e
    }
}
