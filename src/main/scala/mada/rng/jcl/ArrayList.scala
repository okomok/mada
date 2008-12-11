
package mada.rng.jcl


import Foreach._
import Size._


// ArrayList[A] -> Expr[Rng[A]]

object ArrayListCompatible extends ArrayListCompatible; trait ArrayListCompatible {
    implicit def madaRngJcl_ArrayList2ExprOfRng[A](from: java.util.ArrayList[A]): Expr.Of[Rng[A]] = FromArrayListExpr(Expr(from)).expr
}


// toRng

object ArrayListToRng extends ArrayListToRng; trait ArrayListToRng extends Predefs {
    class MadaRngArrayListToRng[A](_1: Expr.Of[java.util.ArrayList[A]]) {
        def toRng = FromArrayListExpr(_1).expr
    }
    implicit def toMadaRngArrayListToRng[A](_1: Expr.Of[java.util.ArrayList[A]]): MadaRngArrayListToRng[A] = new MadaRngArrayListToRng[A](_1)
}

case class FromArrayListExpr[A](_1: Expr.Of[java.util.ArrayList[A]]) extends Expr[java.util.ArrayList[A], Rng[A]] {
    override protected def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ToArrayListExpr(x1) => x1.eval
            case _ => aux.eval
        }
        case _ => dontKnow(x)
    }

    private def aux = IndexAccessRngExpr(new ArrayListIndexAccess(_1.eval))
}

class ArrayListIndexAccess[A](val base: java.util.ArrayList[A]) extends IndexAccess[A] {
    override def _set(i: Long, e: A) = { base.set(i.toInt, e) }
    override def _get(i: Long) = base.get(i.toInt)
    override def _size = base.size
}


// toArrayList

object ToArrayList extends ToArrayList; trait ToArrayList extends Predefs {
    class MadaRngToArrayList[A](_1: Expr.Of[Rng[A]]) {
        def jcl_toArrayList = ToArrayListExpr(_1).expr
    }
    implicit def toMadaRngToArrayList[A](_1: Expr.Of[Rng[A]]): MadaRngToArrayList[A] = new MadaRngToArrayList[A](_1)
}

case class ToArrayListExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], java.util.ArrayList[A]] {
    override protected def _default = _1 match {
        case FromArrayListExpr(x1) => x1.eval
        case _ => ToArrayListImpl(_1.eval)
    }
}

object ToArrayListImpl {
    def apply[A](r: Rng[A]): java.util.ArrayList[A] = {
        var a = newArrayList(r)
        r./.foreach(a.add(_: A))./
        a
    }

    private def newArrayList[A](r: Rng[A]) = r.traversal match {
        case _: RandomAccessTraversal => new java.util.ArrayList[A](r./.size./.toInt)
        case _: SinglePassTraversal => new java.util.ArrayList[A]
    }
}


// ArrayList utilities

object ArrayList {
    def apply[A](es: A*): java.util.ArrayList[A] = {
        val a = new java.util.ArrayList[A](es.length)
        for (e <- es.elements) a.add(e)
        a
    }
}
