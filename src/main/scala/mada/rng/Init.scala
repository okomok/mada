

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Init extends Init; trait Init extends Predefs {
    class MadaRngInit[A](_1: Expr.Of[Rng[A]]) {
        def init = InitExpr(_1).expr
    }
    implicit def toMadaRngInit[A](_1: Expr.Of[Rng[A]]): MadaRngInit[A] = new MadaRngInit[A](_1)
}


case class InitExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Transform[Rng[A]] {
    override protected def _default = InitImpl(_1.eval)
}


object InitImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertNotEmpty(r)
        val (p, q) = r.toPair
        p.traversal match {
            case _: Traversal.Bidirectional => p <=< --(q)
            case _: Traversal.Forward => new ForwardInitPointer(p, q) <=< new ForwardInitPointer(q, q)
            case _: Traversal.SinglePass => new SinglePassInitPointer(p, false) <=< new SinglePassInitPointer(q, true)
        }
    }
}


class ForwardInitPointer[A](override protected val _base: Pointer[A], end: Pointer[A])
        extends PointerAdapter[A, A, ForwardInitPointer[A]] {
    Assert("doh", _base.traversal == Traversal.Forward)
    lookNext
    override protected def _increment = { base.pre_++; lookNext }
    override protected def _copy = new ForwardInitPointer(base.copy, end)
    private def lookNext = { if (base.copy.pre_++ == end) { baseRef := end } }
}

class SinglePassInitPointer[A](override protected val _base: Pointer[A], fromEnd: Boolean)
        extends PointerAdapter[A, A, SinglePassInitPointer[A]] {
    Assert("doh", _base.traversal == Traversal.SinglePass)
    private var tmp: A = _
    if (!fromEnd) { _increment }
    override protected def _read = tmp
    override protected def _write(e: A) = { throw new NotWritablePointerError(this) }
    override protected def _increment = { tmp = *(base); base.pre_++ }
}
