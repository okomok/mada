
package mada.rng


object ReadOnly extends ReadOnly; trait ReadOnly extends Predefs {
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
        val (p, q) = r.toPair
        new ReadOnlyPointer(p) <=< new ReadOnlyPointer(q)
    }
}

class ReadOnlyPointer[A](val base: Pointer[A]) extends Pointer[A] {
    override def _read = base.read
    override def _write(e: A) { throw new ErrorNotWritable(this) }
    override def _traversal = base.traversal
    override def _equals_(that: Pointer[A]): Boolean = this.plain == that.plain
    override def _increment { base.pre_++ }
    override def _copy = new ReadOnlyPointer(base.copy)
    override def _decrement { base.pre_-- }
    override def _offset(d: Long) { base += d }
    override def _difference_(that: Pointer[A]): Long = this.plain - that.plain

    override def plain = base.plain
    override def readOnly = this
}
