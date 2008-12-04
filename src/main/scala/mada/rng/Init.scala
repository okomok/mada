
package mada.rng


import Pointer._


object Init extends Init; trait Init extends Predefs {
    class MadaRngInit[A](_1: Expr[Rng[A]]) {
        def rng_init = InitExpr(_1).expr
    }
    implicit def toMadaRngInit[A](_1: Expr[Rng[A]]): MadaRngInit[A] = new MadaRngInit[A](_1)
}


case class InitExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = InitImpl(_1.eval)
}


object InitImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertNotEmpty(r)
        val (p, q) = r.toPair
        p.traversal match {
            case _: BidirectionalTraversal => p <=< --(q)
            case _: ForwardTraversal => new ForwardInitPointer(p, q) <=< new ForwardInitPointer(q, q)
            case _: SinglePassTraversal => new SinglePassInitPointer(p, false) <=< new SinglePassInitPointer(q, true)
        }
    }
}


class ForwardInitPointer[A](override val _base: Pointer[A], end: Pointer[A])
        extends PointerAdapter[A, A, ForwardInitPointer[A]] {
    Assert("doh", _base.traversal == ForwardTraversal)
    lookNext
    override def _increment = { base.pre_++; lookNext }
    override def _copy = new ForwardInitPointer(base.copy, end)
    private def lookNext = { if (base.copy.pre_++ == end) { baseRef := end } }
}

class SinglePassInitPointer[A](override val _base: Pointer[A], fromEnd: Boolean)
        extends PointerAdapter[A, A, SinglePassInitPointer[A]] {
    Assert("doh", _base.traversal == SinglePassTraversal)
    private var tmp: A = _
    if (!fromEnd) { _increment }
    override def _read = tmp
    override def _write(e: A) = { throw new NotWritablePointerError(this) }
    override def _increment = { tmp = *(base); base.pre_++ }
}
