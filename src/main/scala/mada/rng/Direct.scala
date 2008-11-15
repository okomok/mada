
package mada.rng


// indirect

object Indirect extends Indirect

trait Indirect extends Predefs {
    class MadaRngIndirect[A](_1: Expr[Rng[Pointer[A]]]) {
        def indirect = IndirectExpr(_1).expr
    }
    implicit def toMadaRngIndirect[A](_1: Expr[Rng[Pointer[A]]]) = new MadaRngIndirect(_1)
}

case class IndirectExpr[A](_1: Expr[Rng[Pointer[A]]]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case OutdirectExpr(x1) => x1.eval
        case _ => IndirectImpl(_1.eval)
    }
}

object IndirectImpl {
    def apply[A](r: Rng[Pointer[A]]): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new IndirectPointer(p) <=< new IndirectPointer(q)
    }
}

class IndirectPointer[A](override val _base: Pointer[Pointer[A]])
        extends PointerAdapter[Pointer[A], A, IndirectPointer[A]] {
    override def _read = *(*(base))
    override def _write(e: A) { *(*(base)) = e }
    override def _clone = new IndirectPointer[A](base.clone)
}


// outdirect

object Outdirect extends Outdirect

trait Outdirect extends Predefs {
    class MadaRngOutdirect[A](_1: Expr[Rng[A]]) {
        def outdirect = OutdirectExpr(_1).expr
    }
    implicit def toMadaRngOutdirect[A](_1: Expr[Rng[A]]) = new MadaRngOutdirect(_1)
}

case class OutdirectExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[Pointer[A]]] {
    override def _eval = _1 match {
        case IndirectExpr(x1) => x1.eval
        case _ => OutdirectImpl(_1.eval)
    }
}

object OutdirectImpl {
    def apply[A](r: Rng[A]): Rng[Pointer[A]] = {
        val (p, q) = (r.begin, r.end)
        new OutdirectPointer(p) <=< new OutdirectPointer(q)
    }
}

class OutdirectPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override def _read = base
    override def _write(e: Pointer[A]) { throw new ErrorNotWritable(this) }
    override def _clone = new OutdirectPointer(base.clone)
}
