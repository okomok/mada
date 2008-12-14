

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Indirect extends Indirect; trait Indirect extends Predefs {
    class MadaRngIndirect[A](_1: Expr.Of[Rng[Pointer[A]]]) {
        def indirect = IndirectExpr(_1).expr
    }
    implicit def toMadaRngIndirect[A](_1: Expr.Of[Rng[Pointer[A]]]): MadaRngIndirect[A] = new MadaRngIndirect[A](_1)
}


case class IndirectExpr[A](_1: Expr.Of[Rng[Pointer[A]]]) extends Expr.Method[Rng[Pointer[A]], Rng[A]] {
    override protected def _default = _1 match {
        case OutdirectExpr(x1) => x1.eval // indirect-outdirect fusion
        case _ => IndirectImpl(_1.eval)
    }
}


object IndirectImpl {
    def apply[A](r: Rng[Pointer[A]]): Rng[A] = {
        val (p, q) = r.toPair
        new IndirectPointer(p) <=< new IndirectPointer(q)
    }
}

class IndirectPointer[A](override protected val _base: Pointer[Pointer[A]])
        extends PointerAdapter[Pointer[A], A, IndirectPointer[A]] {
    override protected def _read = *(*(base))
    override protected def _write(e: A) = *(*(base)) = e
    override protected def _copy = new IndirectPointer[A](base.copy)
    override protected def _offsetRead(d: Long) = *(*(base), + d)
    override protected def _offsetWrite(d: Long, e: A) = *(*(base), + d) = e
}
