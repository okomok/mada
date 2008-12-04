
package mada.rng


import Pointer._


object Pop extends Pop; trait Pop extends Predefs {
    class MadaRngPop[A](_1: Expr[Rng[A]]) {
        def rng_pop = PopExpr(_1).expr
    }
    implicit def toMadaRngPop[A](_1: Expr[Rng[A]]): MadaRngPop[A] = new MadaRngPop[A](_1)
}


case class PopExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = PopImpl(_1.eval)
}


object PopImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertNotEmpty(r)
        val (p, q) = r.toPair
        p.traversal match {
            case _: BidirectionalTraversal => p <=< --(q)
            case _: ForwardTraversal => new ForwardPopPointer(p, q) <=< new ForwardPopPointer(q, q)
            case _: SinglePassTraversal => new SinglePassPopPointer(p, false) <=< new SinglePassPopPointer(q, true)
        }
    }
}


class ForwardPopPointer[A](override val _base: Pointer[A], end: Pointer[A])
        extends PointerAdapter[A, A, ForwardPopPointer[A]] {
    Assert("doh", _base.traversal == ForwardTraversal)
    lookNext
    override def _increment = { base.pre_++; lookNext }
    override def _copy = new ForwardPopPointer(base.copy, end)
    private def lookNext = { if (base.copy.pre_++ == end) { baseRef := end } }
}

class SinglePassPopPointer[A](override val _base: Pointer[A], fromEnd: Boolean)
        extends PointerAdapter[A, A, SinglePassPopPointer[A]] {
    Assert("doh", _base.traversal == SinglePassTraversal)
    private var tmp: A = _
    if (!fromEnd) { _increment }
    override def _read = tmp
    override def _write(e: A) = { throw new NotWritablePointerError(this) }
    override def _increment = { tmp = *(base); base.pre_++ }
}
