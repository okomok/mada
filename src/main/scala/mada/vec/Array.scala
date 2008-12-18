

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Array[A] -> Expr[Vector[A]]

object ArrayCompatible extends ArrayCompatible; trait ArrayCompatible {
    implicit def madaVec_Array2ExprOfVector[A](from: Array[A]): Expr.Of[Vector[A]] = FromArrayExpr(Expr(from)).expr
}


// toVector

object ArrayToVector extends ArrayToVector; trait ArrayToVector extends Predefs {
    class MadaVecArrayToVector[A](_1: Expr.Of[Array[A]]) {
        def toVector = FromArrayExpr(_1).expr
    }
    implicit def toMadaVecArrayToVector[A](_1: Expr.Of[Array[A]]): MadaVecArrayToVector[A] = new MadaVecArrayToVector[A](_1)
}

case class FromArrayExpr[A](_1: Expr.Of[Array[A]]) extends Expr[Array[A], Vector[A]] {
    override protected def _eval[U](x: Expr[Vector[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ToArrayExpr(x1) => x1.eval
            case _ => new ArrayVector(_1.eval)
        }
        case _ => dontKnow(x)
    }
}

class ArrayVector[A](val array: Array[A]) extends Vector[A] {
    override def size = array.length
    override def apply(i: Long) = array(i.toInt)
    override def update(i: Long, e: A) = array(i.toInt) = e
}


// toArray

object ToArray extends ToArray; trait ToArray extends Predefs {
    class MadaVecToArray[A](_1: Expr.Of[Vector[A]]) {
        def toArray = ToArrayExpr(_1).expr
    }
    implicit def toMadaVecToArray[A](_1: Expr.Of[Vector[A]]): MadaVecToArray[A] = new MadaVecToArray[A](_1)
}

case class ToArrayExpr[A](override val _1: Expr.Of[Vector[A]]) extends Expr.Method[Vector[A], Array[A]] {
    override protected def _default = _1 match {
        case FromArrayExpr(x1) => x1.eval
        case _ => ToArrayImpl(_1.eval)
    }
}

object ToArrayImpl {
    import Foreach._

    def apply[A](v: Vector[A]): Array[A] = {
        val a = new Array[A](v.size.toInt)
        var i = 0
        v./.foreach({ (e: A) => a(i) = e; i += 1 })./
        a
    }
}
