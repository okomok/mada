
package mada.rng


// toRng

trait IteratorToRng {
    class MadaRngIteratorToRng[A](base: Expr[Iterator[A]]) {
        def toRng = FromIteratorExpr(base).expr
    }
    implicit def toMadaRngIteratorToRng[A](base: Expr[Iterator[A]]) = new MadaRngIteratorToRng(base)
}

case class FromIteratorExpr[A](base: Expr[Iterator[A]]) extends Expr[Rng[A]] {
    override def eval = base match {
        case ToIteratorExpr(b) => b.eval
        case _ => new IteratorRng(base.eval)
    }
}

class IteratorRng[A](val base: Iterator[A]) extends Rng[A] {
    override def _begin = new IteratorPointer(base, if (base.hasNext) Some(base.next) else None)
    override def _end = new IteratorPointer(base, None)
}

class IteratorPointer[A](base: Iterator[A], private var e: Option[A])
        extends PointerFacade[A, IteratorPointer[A]] {
    override def _read = e.get
    override def _traversal = SinglePassTraversal
    override def _equals(that: IteratorPointer[A]) = e.isEmpty == that.e.isEmpty
    override def _increment { if (base.hasNext) e = Some(base.next) else e = None; }
}


// toIterator

object ToIterator extends ToIterator

trait ToIterator {
    class MadaRngToIterator[A](base: Expr[Rng[A]]) {
        def toIterator = ToIteratorExpr(base).expr
    }
    implicit def toMadaRngToIterator[A](base: Expr[Rng[A]]) = new MadaRngToIterator(base)
}

case class ToIteratorExpr[A](base: Expr[Rng[A]]) extends Expr[Iterator[A]] {
    override def eval = base match {
        case FromIteratorExpr(b) => b.eval
        case _ => new RngIterator(base.eval)
    }
}

class RngIterator[A](val base: Rng[A]) extends Iterator[A] {
    private val p = base.begin
    private val q = base.end
    override def hasNext = p != q
    override def next = { p++/; p.read }
}
