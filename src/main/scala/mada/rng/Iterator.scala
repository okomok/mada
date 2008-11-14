
package mada.rng



// Iterator[A] <-> Expr[Rng[A]]

trait IteratorConversion {
    implicit def toMadaIteratorRngExpr[A](from: => Iterator[A]) = FromIteratorExpr(Expr(from)).expr
    implicit def fromMadaIteratorRngExpr[A](from: Expr[Rng[A]]) = ToIteratorExpr(from).eval
}


// toRng

trait IteratorToRng {
    class MadaRngIteratorToRng[A](_1: Expr[Iterator[A]]) {
        def toRng = FromIteratorExpr(_1).expr
    }
    implicit def toMadaRngIteratorToRng[A](_1: Expr[Iterator[A]]) = new MadaRngIteratorToRng(_1)
}

case class FromIteratorExpr[A](_1: Expr[Iterator[A]]) extends Expr[Rng[A]] {
    override def eval = _1 match {
        case ToIteratorExpr(a1) => a1.eval
        case _ => new IteratorRng(_1.eval)
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
    class MadaRngToIterator[A](_1: Expr[Rng[A]]) {
        def toIterator = ToIteratorExpr(_1).expr
    }
    implicit def toMadaRngToIterator[A](_1: Expr[Rng[A]]) = new MadaRngToIterator(_1)
}

case class ToIteratorExpr[A](_1: Expr[Rng[A]]) extends Expr[Iterator[A]] {
    override def eval = _1 match {
        case FromIteratorExpr(a1) => a1.eval
        case _ => new RngIterator(_1.eval)
    }
}

class RngIterator[A](val base: Rng[A]) extends Iterator[A] {
    private val p = base.begin
    private val q = base.end
    override def hasNext = p != q
    override def next = { p++/; p.read }
}
