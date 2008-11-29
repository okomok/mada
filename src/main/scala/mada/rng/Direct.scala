
package mada.rng


import Pointer._


// indirect

object Indirect extends Indirect; trait Indirect extends Predefs {
    class MadaRngIndirect[A](_1: Expr[Rng[Pointer[A]]]) {
        def rng_indirect = IndirectExpr(_1).expr
    }
    implicit def toMadaRngIndirect[A](_1: Expr[Rng[Pointer[A]]]): MadaRngIndirect[A] = new MadaRngIndirect[A](_1)
}

case class IndirectExpr[A](_1: Expr[Rng[Pointer[A]]]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
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

class IndirectPointer[A](override val _base: Pointer[Pointer[A]])
        extends PointerAdapter[Pointer[A], A, IndirectPointer[A]] {
    override def _read = *(*(base))
    override def _write(e: A) { *(*(base)) = e }
    override def _copy = new IndirectPointer[A](base.copy)
}


// outdirect

object Outdirect extends Outdirect; trait Outdirect extends Predefs {
    class MadaRngOutdirect[A](_1: Expr[Rng[A]]) {
        def rng_outdirect = OutdirectExpr(_1).expr
    }
    implicit def toMadaRngOutdirect[A](_1: Expr[Rng[A]]): MadaRngOutdirect[A] = new MadaRngOutdirect[A](_1)
}

case class OutdirectExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[Pointer[A]]] {
    override def _eval = _1 match {
        case IndirectExpr(x1) => x1.eval // outdirect-indirect fusion
        case _ => OutdirectImpl(_1.eval)
    }
    override def _eval[U](c: Context[Rng[Pointer[A]], U]): U = c match {
        case LoopContext(f) => OutdirectLoopImpl(_1.eval, f) // loop-outdirect fusion
        case _ => super._eval(c)
    }
}

object OutdirectImpl {
    def apply[A](r: Rng[A]): Rng[Pointer[A]] = {
        val (p, q) = r.toPair
        new OutdirectPointer(p) <=< new OutdirectPointer(q)
    }
}

class OutdirectPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override def _read = base
    override def _write(e: Pointer[A]) { throw new ErrorNotWritable(this) }
    override def _copy = new OutdirectPointer(base.copy)
}

object OutdirectLoopImpl {
    def apply[A](r: Rng[A], f: Pointer[A] => Boolean) {
        val (p, q) = r.toPair
        while (p != q && f(p))
            ++(p)
    }
}
