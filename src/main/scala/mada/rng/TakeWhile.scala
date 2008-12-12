

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object TakeWhile extends TakeWhile; trait TakeWhile extends Predefs {
    class MadaRngTakeWhile[A](_1: Expr.Of[Rng[A]]) {
        def takeWhile(_2: A => Boolean) = TakeWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngTakeWhile[A](_1: Expr.Of[Rng[A]]): MadaRngTakeWhile[A] = new MadaRngTakeWhile[A](_1)
}


case class TakeWhileExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Transform[Rng[A]] {
    override protected def _default = TakeWhileImpl(_1.eval, _2)
}


object TakeWhileImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = r.toPair
        new TakeWhilePointer(p, q, f) <=< new TakeWhilePointer(q, q, f)
    }
}

class TakeWhilePointer[A](override protected val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
    taken
    override protected def _traversal = base.traversal upper Traversal.Forward
    override protected def _increment = { base.pre_++; taken }
    override protected def _copy = new TakeWhilePointer(base.copy, end, predicate)
    override def toString = new StringBuilder().append("TakeWhilePointer of ").append(base).toString

    private def taken = {
        if (base != end && !predicate(*(base))) {
            baseRef := end
        }
    }
}
