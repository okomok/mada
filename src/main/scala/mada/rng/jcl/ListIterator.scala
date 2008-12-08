
package mada.rng.jcl


/*
import java.util.ListIterator
import Pointer._


// LinkedList <-> Expr[Rng[A]]

object LinkedListCompatible extends LinkedListCompatible; trait LinkedListCompatible {
    implicit def madaRngJcl_LinkedList2RngExpr[A](from: LinkedList[A]): Expr[Rng[A]] = FromLinkedListExpr(Expr(from)).expr
}


// toRng

object LinkedListToRng extends LinkedListToRng; trait LinkedListToRng extends Predefs {
    class MadaRngJclLinkedListToRng[A](_1: Expr[LinkedList[A]]) {
        def toRng = FromLinkedListExpr(_1).expr
    }
    implicit def toMadaRngJclLinkedListToRng[A](_1: Expr[LinkedList[A]]): MadaRngJclLinkedListToRng[A] = new MadaRngJclLinkedListToRng[A](_1)
}

case class FromLinkedListExpr[A](_1: Expr[LinkedList[A]]) extends Expr[Rng[A]] {
    override protected def _eval = _1 match {
        case ToLinkedListExpr(x1) => x1.eval
        case _ => FromLinkedListImpl(_1.eval)
    }
}

object FromLinkedListImpl {
    def apply[A](it: LinkedList[A]) = {
        new LinkedListPointer(l.listIterator(0), l) <=<
            new LinkedListPointer(l.listIterator(l.size), l) // linear
    }
}

class LinkedListPointer(val base: ListIterator[A], val list: LinkedList) extends PointerFacade[A, LinkedListPointer[A]] {
    override protected def _read = { val tmp = base.next; base.previous; tmp }
    override protected def _traversal = BidirectionalTraversal
    override protected def _equals(that: LinkedListPointer[A]) = base.nextIndex == that.base.nextIndex
    override protected def _increment = { base.next }
    override protected def _copy = new LinkedListPointer(list.listIterator(base.nextIndex), list) // linear
    override protected def _decrement = { base.previous }
    override def hashCode = base.hashCode
}


// toListIterator

object ToListIterator extends ToListIterator; trait ToListIterator extends Predefs {
    class MadaRngToListIterator[A](_1: Expr[Rng[A]]) {
        def toListIterator = ToListIteratorExpr(_1).expr
    }
    implicit def toMadaRngToListIterator[A](_1: Expr[Rng[A]]): MadaRngToListIterator[A] = new MadaRngToListIterator[A](_1)
}

case class ToListIteratorExpr[A](_1: Expr[Rng[A]]) extends Expr[ListIterator[A]] {
    override protected def _eval = _1.eval(ToListIteratorContext[A]())
}

case class ToListIteratorContext[A] extends Context[Rng[A], ListIterator[A]] {
    override def apply(_1: Expr[Rng[A]]) = _1 match {
        case FromListIteratorExpr(x1) => x1.eval
        case _ => new RngListIterator(_1.eval)
    }
}

class RngListIterator[A](val base: Rng[A]) extends ListIterator[A] {
    AssertModels(base, BidirectionalTraversal)

    private val (begin, end) = base.toPair
    private val p = begin.copy
    private var index = 0
    private var latest: Pointer[A] = null

    override def hasNext = p != end
    override def nextIndex = index
    override def hasPrevious = p != begin
    override def next = { latest = p.copy; p.pre_++; index += 1; latest.read }
    override def previous = { p.pre_--; index -= 1; latest = p.copy; latest.read }
    override def previousIndex = index - 1
    override def set(e: A) = { latest.write(e) }
}
*/

