

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.{ ArrayList => JavaArrayList }


// JavaArrayList[A] -> Expr[Vector[A]]

object ArrayListCompatible extends ArrayListCompatible; trait ArrayListCompatible {
    implicit def madaVecJcl_ArrayList2ExprOfVector[A](from: JavaArrayList[A]): Expr.Of[Vector[A]] = FromArrayListExpr(Expr(from)).expr
}


// toVector

object ArrayListToVector extends ArrayListToVector; trait ArrayListToVector extends Predefs {
    class MadaVecArrayListToVector[A](_1: Expr.Of[JavaArrayList[A]]) {
        def toVector = FromArrayListExpr(_1).expr
    }
    implicit def toMadaVecArrayListToVector[A](_1: Expr.Of[JavaArrayList[A]]): MadaVecArrayListToVector[A] = new MadaVecArrayListToVector[A](_1)
}

case class FromArrayListExpr[A](override val _1: Expr.Of[JavaArrayList[A]]) extends Expr.Method[JavaArrayList[A], Vector[A]] {
    override protected def _default = _1 match {
        case ToArrayListExpr(x1) => x1.eval
        case _ => new ArrayListVector(_1.eval)
    }
}

class ArrayListVector[A](val arrayList: JavaArrayList[A]) extends Vector[A] {
    override def size = arrayList.size
    override def apply(i: Long) = arrayList.get(i.toInt)
    override def update(i: Long, e: A) = arrayList.set(i.toInt, e)
}


// toArrayList

object ToArrayList extends ToArrayList; trait ToArrayList extends Predefs {
    class MadaVecToJavaArrayList[A](_1: Expr.Of[Vector[A]]) {
        def jcl_toArrayList = ToArrayListExpr(_1).expr
    }
    implicit def toMadaVecToJavaArrayList[A](_1: Expr.Of[Vector[A]]): MadaVecToJavaArrayList[A] = new MadaVecToJavaArrayList[A](_1)
}

case class ToArrayListExpr[A](override val _1: Expr.Of[Vector[A]]) extends Expr.Method[Vector[A], JavaArrayList[A]] {
    override protected def _default = _1 match {
        case FromArrayListExpr(x1) => x1.eval
        case _ => ToArrayListImpl(_1.eval)
    }
}

object ToArrayListImpl {
    import Foreach._

    def apply[A](v: Vector[A]): JavaArrayList[A] = {
        var a = new JavaArrayList[A](v.size.toInt)
        v./.foreach(a.add(_: A))./
        a
    }
}


// utilities

object ArrayList {
    def apply[A](es: A*): JavaArrayList[A] = {
        val a = new JavaArrayList[A](es.length)
        for (e <- es.elements) {
            a.add(e)
        }
        a
    }
}
