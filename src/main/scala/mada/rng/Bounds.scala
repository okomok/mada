
package mada.rng


object Bounds extends Bounds; trait Bounds extends Predefs {
    class MadaRngBounds[A](_1: ExprV2.Of[Rng[A]]) {
        def bounds = BoundsExpr(_1).expr
    }
    implicit def toMadaRngBounds[A](_1: ExprV2.Of[Rng[A]]): MadaRngBounds[A] = new MadaRngBounds[A](_1)
}


case class BoundsExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], Rng[A]] {
    override protected def _default = _1 match {
        case y @ BoundsExpr(x1) => y.eval // bounds-bounds fusion
        case _ => BoundsImpl(_1.eval)
    }
}


object BoundsImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertModels(r, ForwardTraversal)
        val (p, q) = r.toPair
        val (ip, iq) = r.toPair
        new BoundsPointer(p, ip, iq) <=< new BoundsPointer(q, ip, iq)
    }
}

class BoundsPointer[A](override val _base: Pointer[A], val begin: Pointer[A], val end: Pointer[A])
        extends PointerAdapter[A, A, BoundsPointer[A]] {
    override def _increment = {
        if (base == end)
            throw new ErrorOutOfBounds(this, begin <=< end, "end-pointer to increment")

        base.pre_++
    }

    override def _copy = new BoundsPointer(base.copy, begin, end)

    override def _decrement = {
        if (base == begin)
            throw new ErrorOutOfBounds(this, begin <=< end, "begin-pointer to decrement")

        base.pre_--
    }

    override def _offset(d: Long) = {
        if (d >= 0 && d > end - base)
            throw new ErrorOutOfBounds(this, begin <=< end, "end-pointer to advance")
        else if (d < 0 && -d > base - begin)
            throw new ErrorOutOfBounds(this, begin <=< end, "begin-pointer to advance")

        base += d
    }
}

class ErrorOutOfBounds[A](val pointer: Pointer[A], val rng: Rng[A], msg: String) extends RuntimeException(msg)
