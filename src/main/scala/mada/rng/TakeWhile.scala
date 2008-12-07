
package mada.rng


object TakeWhile extends TakeWhile; trait TakeWhile extends Predefs {
    class MadaRngTakeWhile[A](_1: ExprV2.Of[Rng[A]]) {
        def takeWhile(_2: A => Boolean) = TakeWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngTakeWhile[A](_1: ExprV2.Of[Rng[A]]): MadaRngTakeWhile[A] = new MadaRngTakeWhile[A](_1)
}


case class TakeWhileExpr[A](override val _1: ExprV2.Of[Rng[A]], _2: A => Boolean) extends ExprV2.Transform[Rng[A]] {
    override def _default = TakeWhileImpl(_1.eval, _2)
}


object TakeWhileImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = r.toPair
        new TakeWhilePointer(p, q, f) <=< new TakeWhilePointer(q, q, f)
    }
}

class TakeWhilePointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
    taken
    override def _traversal = base.traversal upper ForwardTraversal
    override def _increment = { base.pre_++; taken }
    override def _copy = new TakeWhilePointer(base.copy, end, predicate)
    override def toString = new StringBuilder().append("TakeWhilePointer of ").append(base).toString

    private def taken = {
        if (base != end && !predicate(*(base))) {
            baseRef := end
        }
    }
}
