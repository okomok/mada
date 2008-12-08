
package mada.rng


object ReadOnly extends ReadOnly; trait ReadOnly extends Predefs {
    class MadaRngReadOnly[A](_1: Expr.Of[Rng[A]]) {
        def readOnly = ReadOnlyExpr(_1).expr
    }
    implicit def toMadaRngReadOnly[A](_1: Expr.Of[Rng[A]]): MadaRngReadOnly[A] = new MadaRngReadOnly[A](_1)
}


case class ReadOnlyExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Transform[Rng[A]] {
    override protected def _default = _1 match {
        case ReadOnlyExpr(x1) => ReadOnlyExpr(x1).eval // readOnly-readOnly fusion
        case _ => ReadOnlyImpl(_1.eval)
    }
}


object ReadOnlyImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        val (p, q) = r.toPair
        new ReadOnlyPointer(p) <=< new ReadOnlyPointer(q)
    }
}

class ReadOnlyPointer[A](override protected val _base: Pointer[A])
        extends PointerAdapter[A, A, ReadOnlyPointer[A]] {
    override protected def _write(e: A) = { throw new NotWritablePointerError(this) }
    override protected def _copy = new ReadOnlyPointer(base.copy)
}
