

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Outdirect extends Outdirect; trait Outdirect extends Predefs {
    class MadaRngOutdirect[A](_1: Expr.Of[Rng[A]]) {
        def outdirect = OutdirectExpr(_1).expr
    }
    implicit def toMadaRngOutdirect[A](_1: Expr.Of[Rng[A]]): MadaRngOutdirect[A] = new MadaRngOutdirect[A](_1)
}


case class OutdirectExpr[A](_1: Expr.Of[Rng[A]]) extends Expr[Rng[A], Rng[Pointer[A]]] {
    override protected def _eval[U](x: Expr[Rng[Pointer[A]], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case IndirectExpr(x1) => x1.eval // outdirect-indirect fusion
            case _ => OutdirectImpl(_1.eval)
        }
        case LoopExpr(_, f) => OutdirectLoopImpl(_1.eval, f) // loop-outdirect fusion
        case _ => dontKnow(x)
    }
}


object OutdirectImpl {
    def apply[A](r: Rng[A]): Rng[Pointer[A]] = {
        val (p, q) = r.toPair
        new OutdirectPointer(p) <=< new OutdirectPointer(q)
    }
}

class OutdirectPointer[A](override protected val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override protected def _read = base
    override protected def _write(e: Pointer[A]) = throw new NotWritablePointerError(this)
    override protected def _copy = new OutdirectPointer(base.copy)
}


object OutdirectLoopImpl {
    def apply[A](r: Rng[A], f: Pointer[A] => Boolean) {
        val (p, q) = r.toPair
        while (p != q && f(p)) {
            ++(p)
        }
    }
}
