
package mada.rng


import ToIterator._


//  List[A] <-> Expr[Rng[A]]

object ListCompatible extends ListCompatible; trait ListCompatible {
    implicit def madaRng_List2ExprRng[A](from: List[A]): ExprV2.Of[Rng[A]] = FromListExpr(ExprV2.Constant(from)).expr
}


// toRng

object ListToRng extends ListToRng; trait ListToRng extends Predefs {
    class MadaRngListToRng[A](_1: ExprV2.Of[List[A]]) {
        def toRng = FromListExpr(_1).expr
    }
    implicit def toMadaRngListToRng[A](_1: ExprV2.Of[List[A]]): MadaRngListToRng[A] = new MadaRngListToRng[A](_1)
}

case class FromListExpr[A](override val _1: ExprV2.Of[List[A]]) extends ExprV2.Method[List[A], Rng[A]] {
    override def _default = _1 match {
        case ToListExpr(x1) => x1.eval
        case _ => FromListImpl(_1.eval)
    }
}

object FromListImpl {
    def apply[A](l: List[A]): Rng[A] = new ListPointer(l) <=< new ListPointer(Nil)
}

class ListPointer[A](var base: List[A]) extends PointerFacade[A, ListPointer[A]] {
    override def _read = base.head
    override def _traversal = ForwardTraversal
    override def _equals(that: ListPointer[A]) = base eq that.base
    override def _increment = { base = base.tail }
    override def _copy = new ListPointer[A](base)
    override def hashCode = base.hashCode
}


// toList

object ToList extends ToList; trait ToList extends Predefs {
    class MadaRngToList[A](_1: ExprV2.Of[Rng[A]]) {
        def toList = ToListExpr(_1).expr
    }
    implicit def toMadaRngToList[A](_1: ExprV2.Of[Rng[A]]): MadaRngToList[A] = new MadaRngToList[A](_1)
}

case class ToListExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], List[A]] {
    override def _default = _1 match {
        case FromListExpr(x1) => x1.eval
        case _ => List.fromIterator(_1.toIterator.eval)
    }
}
