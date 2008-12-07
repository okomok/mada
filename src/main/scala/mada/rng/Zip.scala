
package mada.rng


object Zip extends Zip; trait Zip extends Predefs {
    class MadaRngZip[A1](_1: ExprV2.Of[Rng[A1]]) {
        def zip[A2](_2: ExprV2.Of[Rng[A2]]) = ZipExpr(_1, _2).expr
    }
    implicit def toMadaRngZip[A1](_1: ExprV2.Of[Rng[A1]]): MadaRngZip[A1] = new MadaRngZip[A1](_1)
}


case class ZipExpr[A1, A2](override val _1: ExprV2.Of[Rng[A1]], _2: ExprV2.Of[Rng[A2]]) extends ExprV2.Method[Rng[A1], Rng[(A1, A2)]] {
    override def _default = ZipImpl(_1.eval, _2.eval)
}


object ZipImpl {
    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2]): Rng[(A1, A2)] = {
        val (p1, q1) = r1.toPair
        val (p2, q2) = r2.toPair
        new ZipPointer(p1, p2) <=< new ZipPointer(q1, q2)
    }
}

class ZipPointer[A1, A2](val base1: Pointer[A1], val base2: Pointer[A2])
        extends PointerFacade[(A1, A2), ZipPointer[A1, A2]] {
    override def _read = (*(base1), *(base2))
    override def _traversal = base1.traversal upper base2.traversal
    override def _equals(that: ZipPointer[A1, A2]) = base1 == that.base1 && base2 == that.base2
    override def _increment = { base1.pre_++; base2.pre_++ }
    override def _copy = new ZipPointer(base1.copy, base2.copy)
    override def _decrement = { base1.pre_--; base2.pre_-- }
    override def _offset(d: Long) = { base1 += d; base2 += d; }
    override def _difference(that: ZipPointer[A1, A2]) = base1 - that.base1

    override def toString = new StringBuilder().append("ZipPointer of (").append(base1).append(", ").append(base2).append(')').toString
}
