
package mada.rng



// Iterator[A] <-> Expr[Rng[A]]

trait IteratorCompatible {
    implicit def toMadaIteratorRngExpr[A](from: => Iterator[A]): Expr[Rng[A]] = FromIteratorExpr(Expr(from)).expr
    implicit def fromMadaIteratorRngExpr[A](from: Expr[Rng[A]]): Iterator[A] = ToIteratorExpr(from).eval
}


// toRng

object IteratorToRng extends IteratorToRng

trait IteratorToRng extends Predefs {
    class MadaRngIteratorToRng[A](_1: Expr[Iterator[A]]) {
        def toRng = FromIteratorExpr(_1).expr
    }
    implicit def toMadaRngIteratorToRng[A](_1: Expr[Iterator[A]]): MadaRngIteratorToRng[A] = new MadaRngIteratorToRng[A](_1)
}

case class FromIteratorExpr[A](_1: Expr[Iterator[A]]) extends Expr[Rng[A]] {
    override def _eval = _1.eval(FromIteratorContext[A]())
    override def _eval[U](c: Context[Rng[A], U]): U = c match {
        case ForallContext(f) => _1.eval.forall(f)
        case _ => super._eval(c)
    }
}

case class FromIteratorContext[A] extends Context[Iterator[A], Rng[A]] {
    override def apply(_1: Expr[Iterator[A]]) = _1 match {
        case ToIteratorExpr(x1) => x1.eval
        case _ => FromIteratorImpl(_1.eval)
    }
}

object FromIteratorImpl {
    def apply[A](it: Iterator[A]): Rng[A] = {
        new IteratorPointer(it, if (it.hasNext) Some(it.next) else None) <=<
            new IteratorPointer(it, None)
    }
}

class IteratorPointer[A](val base: Iterator[A], private var e: Option[A])
        extends PointerFacade[A, IteratorPointer[A]] {
    override def _read = e.get
    override def _traversal = SinglePassTraversal
    override def _equals(that: IteratorPointer[A]) = e.isEmpty == that.e.isEmpty
    override def _increment { if (base.hasNext) e = Some(base.next) else e = None }
}


// toIterator

object ToIterator extends ToIterator

trait ToIterator extends Predefs {
    class MadaRngToIterator[A](_1: Expr[Rng[A]]) {
        def rng_toIterator = ToIteratorExpr(_1).expr
    }
    implicit def toMadaRngToIterator[A](_1: Expr[Rng[A]]): MadaRngToIterator[A] = new MadaRngToIterator[A](_1)
}

case class ToIteratorExpr[A](_1: Expr[Rng[A]]) extends Expr[Iterator[A]] {
    override def _eval = _1.eval(ToIteratorContext[A]())
}

case class ToIteratorContext[A] extends Context[Rng[A], Iterator[A]] {
    override def apply(_1: Expr[Rng[A]]) = _1 match {
        case FromIteratorExpr(x1) => x1.eval
        case _ => new RngIterator(_1.eval)
    }
}

class RngIterator[A](val base: Rng[A]) extends Iterator[A] {
    private val p = base.begin
    private val q = base.end
    override def hasNext = p != q
    override def next = { p.pre_++; p.read }
}
