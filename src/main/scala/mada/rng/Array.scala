
package mada.rng


import Distance._
import Force._
import Foreach._


//  Array[A] -> Expr[Rng[A]]

object ArrayCompatible extends ArrayCompatible; trait ArrayCompatible {
    implicit def madaRng_Array2ExprOfRng[A](from: Array[A]): Expr.Of[Rng[A]] = FromArrayExpr(Expr(from)).expr
}


// toRng

object ArrayToRng extends ArrayToRng; trait ArrayToRng extends Predefs {
    class MadaRngArrayToRng[A](_1: Expr.Of[Array[A]]) {
        def toRng = FromArrayExpr(_1).expr
    }
    implicit def toMadaRngArrayToRng[A](_1: Expr.Of[Array[A]]): MadaRngArrayToRng[A] = new MadaRngArrayToRng[A](_1)
}

case class FromArrayExpr[A](_1: Expr.Of[Array[A]]) extends Expr[Array[A], Rng[A]] {
    override protected def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ToArrayExpr(x1) => x1.eval
            case _ => aux.eval
        }
        case _ => aux.eval(x)
    }

    private def aux = IndexAccessRngExpr(new ArrayIndexAccess(_1.eval))
}

class ArrayIndexAccess[A](val base: Array[A]) extends IndexAccess[A] {
    override def _set(i: Long, e: A) = { base(i.toInt) = e }
    override def _get(i: Long) = base(i.toInt)
    override def _size = base.length
    override def toString = "ArrayIndexAccess"
}


// toArray

object ToArray extends ToArray; trait ToArray extends Predefs {
    class MadaRngToArray[A](_1: Expr.Of[Rng[A]]) {
        def toArray = ToArrayExpr(_1).expr
    }
    implicit def toMadaRngToArray[A](_1: Expr.Of[Rng[A]]): MadaRngToArray[A] = new MadaRngToArray[A](_1)
}

case class ToArrayExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Array[A]] {
    override protected def _default = _1 match {
        case FromArrayExpr(x1) => x1.eval
        case _ => ToArrayImpl(_1.eval)
    }
}

object ToArrayImpl {
    def apply[A](r: Rng[A]): Array[A] = r.traversal match {
        case _: ForwardTraversal => inForward(r)
        case _: SinglePassTraversal => inForward(r./.force./)
    }

    private def inForward[A](r: Rng[A]): Array[A] = {
        val a = new Array[A](r./.distance./.toInt)
        var i = 0
        r./.foreach({ (e: A) => a(i) = e; i += 1 })./
        a
    }
}
