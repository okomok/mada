
package mada.rng



// Iterator[A] <-> Expr[Rng[A]]

trait IteratorCompatible {
    implicit def madaRng_Iterator2ExprRng[A](from: Iterator[A]): Expr.Of[Rng[A]] = FromIteratorExpr(Expr.Constant(from)).expr
}


// toRng

object IteratorToRng extends IteratorToRng; trait IteratorToRng extends Predefs {
    class MadaRngIteratorToRng[A](_1: Expr.Of[Iterator[A]]) {
        def toRng = FromIteratorExpr(_1).expr
    }
    implicit def toMadaRngIteratorToRng[A](_1: Expr.Of[Iterator[A]]): MadaRngIteratorToRng[A] = new MadaRngIteratorToRng[A](_1)
}

case class FromIteratorExpr[A](_1: Expr.Of[Iterator[A]]) extends Expr[Iterator[A], Rng[A]] {
    override protected def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ToIteratorExpr(x1) => x1.eval
            case _ => FromIteratorImpl(_1.eval)
        }
        case ForallExpr(_, f) => _1.eval.forall(f)
        case _ => dontKnow(x)
    }
}

object FromIteratorImpl {
    def apply[A](it: Iterator[A]): Rng[A] = {
        new IteratorPointer(it, if (it.hasNext) Some(it.next) else None) <=<
            new IteratorPointer(it, None)
    }
}

// null can't replace Option in case A is an AnyVal.
class IteratorPointer[A](val base: Iterator[A], private var e: Option[A])
        extends PointerFacade[A, IteratorPointer[A]] {
    override protected def _read = e.get
    override protected def _traversal = SinglePassTraversal
    override protected def _equals(that: IteratorPointer[A]) = e.isEmpty == that.e.isEmpty
    override protected def _increment = { e = if (base.hasNext) Some(base.next) else None }
}


// toIterator

object ToIterator extends ToIterator; trait ToIterator extends Predefs {
    class MadaRngToIterator[A](_1: Expr.Of[Rng[A]]) {
        def toIterator = ToIteratorExpr(_1).expr
        def elements = toIterator
    }
    implicit def toMadaRngToIterator[A](_1: Expr.Of[Rng[A]]): MadaRngToIterator[A] = new MadaRngToIterator[A](_1)
}

case class ToIteratorExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Iterator[A]] {
    override protected def _default = _1 match {
        case FromIteratorExpr(x1) => x1.eval
        case _ => new RngIterator(_1.eval)
    }
}

class RngIterator[A](val base: Rng[A]) extends Iterator[A] {
    private val (p, q) = base.toPair
    override def hasNext = p != q
    override def next = { val tmp = p.read; p.pre_++; tmp }
}
