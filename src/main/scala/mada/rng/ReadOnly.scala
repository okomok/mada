
package mada.rng


object ReadOnly extends ReadOnly

trait ReadOnly extends Predefs {
    class MadaRngReadOnly[A](_1: Expr[Rng[A]]) {
        def rng_readOnly = ReadOnlyExpr(_1).expr
    }
    implicit def toMadaRngReadOnly[A](_1: Expr[Rng[A]]): MadaRngReadOnly[A] = new MadaRngReadOnly[A](_1)
}


case class ReadOnlyExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case x: ReadOnlyExpr[_] => x.eval
        case _ => ReadOnlyImpl(_1.eval)
    }
}


object ReadOnlyImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new ReadOnlyPointer(p) <=< new ReadOnlyPointer(q)
    }
}

class ReadOnlyPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReadOnlyPointer[A]] {
    override def _write(e: A) { throw new ErrorNotWritable(this) }
    override def _copy = new ReadOnlyPointer(base.copy)
}
