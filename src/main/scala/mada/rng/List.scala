

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import ToIterator._


//  List[A] -> Expr[Rng[A]]

object ListCompatible extends ListCompatible; trait ListCompatible {
    implicit def madaRng_List2ExprOfRng[A](from: List[A]): Expr.Of[Rng[A]] = FromListExpr(Expr(from)).expr
}


// toRng

object ListToRng extends ListToRng; trait ListToRng extends Predefs {
    class MadaRngListToRng[A](_1: Expr.Of[List[A]]) {
        def toRng = FromListExpr(_1).expr
    }
    implicit def toMadaRngListToRng[A](_1: Expr.Of[List[A]]): MadaRngListToRng[A] = new MadaRngListToRng[A](_1)
}

case class FromListExpr[A](override val _1: Expr.Of[List[A]]) extends Expr.Method[List[A], Rng[A]] {
    override protected def _default = _1 match {
        case ToListExpr(x1) => x1.eval
        case _ => FromListImpl(_1.eval)
    }
}

object FromListImpl {
    def apply[A](l: List[A]): Rng[A] = new ListPointer(l) <=< new ListPointer(Nil)
}

class ListPointer[A](var base: List[A]) extends PointerFacade[A, ListPointer[A]] {
    override protected def _read = base.head
    override protected def _traversal = Forward
    override protected def _equals(that: ListPointer[A]) = base eq that.base
    override protected def _increment = base = base.tail
    override protected def _copy = new ListPointer[A](base)
    override def hashCode = base.hashCode
}


// toList

object ToList extends ToList; trait ToList extends Predefs {
    class MadaRngToList[A](_1: Expr.Of[Rng[A]]) {
        def toList = ToListExpr(_1).expr
    }
    implicit def toMadaRngToList[A](_1: Expr.Of[Rng[A]]): MadaRngToList[A] = new MadaRngToList[A](_1)
}

case class ToListExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], List[A]] {
    override protected def _default = _1 match {
        case FromListExpr(x1) => x1.eval
        case _ => List.fromIterator(_1.toIterator.eval)
    }
}
