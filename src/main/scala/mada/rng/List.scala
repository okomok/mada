
package mada.rng


import ToIterator._


//  List[A] <-> Expr[Rng[A]]

object ListCompatible extends ListCompatible; trait ListCompatible {
    implicit def toMadaListRngExpr[A](from: => List[A]): Expr[Rng[A]] = FromListExpr(Expr(from)).expr
    implicit def fromMadaListRngExpr[A](from: Expr[Rng[A]]): List[A] = ToListExpr(from).eval
}


// toRng

object ListToRng extends ListToRng; trait ListToRng extends Predefs {
    class MadaRngListToRng[A](_1: Expr[List[A]]) {
        def toRng = FromListExpr(_1).expr
    }
    implicit def toMadaRngListToRng[A](_1: Expr[List[A]]): MadaRngListToRng[A] = new MadaRngListToRng[A](_1)
}

case class FromListExpr[A](_1: Expr[List[A]]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
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
    override def _increment { base = base.tail }
    override def _copy = new ListPointer[A](base)
    override def hashCode = base.hashCode
}


// toList

object ToList extends ToList; trait ToList extends Predefs {
    class MadaRngToList[A](_1: Expr[Rng[A]]) {
        def rng_toList = ToListExpr(_1).expr
    }
    implicit def toMadaRngToList[A](_1: Expr[Rng[A]]): MadaRngToList[A] = new MadaRngToList[A](_1)
}

case class ToListExpr[A](_1: Expr[Rng[A]]) extends Expr[List[A]] {
    override def _eval = _1 match {
        case FromListExpr(x1) => x1.eval
        case _ => List.fromIterator(_1.rng_toIterator.eval)
    }
}
