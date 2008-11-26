
package mada.rng


object TakeWhile extends TakeWhile

trait TakeWhile extends Predefs {
    class MadaRngTakeWhile[A](_1: Expr[Rng[A]]) {
        def rng_takeWhile(_2: A => Boolean) = TakeWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngTakeWhile[A](_1: Expr[Rng[A]]): MadaRngTakeWhile[A] = new MadaRngTakeWhile[A](_1)
}


case class TakeWhileExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Rng[A]] {
    override def _eval = TakeWhileImpl(_1.eval, _2)
}


object TakeWhileImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new TakeWhilePointer(p, q, f) <=< new TakeWhilePointer(q, q, f)
    }
}

class TakeWhilePointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
    countDown
    override def _traversal = base.traversal upper ForwardTraversal
    override def _increment { base.pre_++; countDown }
    override def _copy = new TakeWhilePointer(base.copy, end, predicate)

    private def countDown {
        if (base == end)
            return
        if (!predicate(*(base)))
            baseRef := end
    }
}
