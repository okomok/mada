
package mada.rng


import Distance._
import Force._
import Foreach._


//  Array[A] <-> Expr[Rng[A]]

object ArrayCompatible extends ArrayCompatible; trait ArrayCompatible {
    implicit def madaRng_Array2ExprRng[A](from: Array[A]): Expr[Rng[A]] = FromArrayExpr(Expr(from)).expr
}


// toRng

object ArrayToRng extends ArrayToRng; trait ArrayToRng extends Predefs {
    class MadaRngArrayToRng[A](_1: Expr[Array[A]]) {
        def toRng = FromArrayExpr(_1).expr
    }
    implicit def toMadaRngArrayToRng[A](_1: Expr[Array[A]]): MadaRngArrayToRng[A] = new MadaRngArrayToRng[A](_1)
}

case class FromArrayExpr[A](_1: Expr[Array[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]): U = c match {
        case DefaultContext => _1 match {
            case ToArrayExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(c)
    }

    private def delegate = IndexAccessRngExpr(new ArrayIndexAccess(_1.eval))
}

class ArrayIndexAccess[A](val base: Array[A]) extends IndexAccess[A] {
    override def _set(i: Long, e: A) = { base(i.toInt) = e }
    override def _get(i: Long) = base(i.toInt)
    override def _size = base.length
    override def toString = "ArrayIndexAccess"
}


// toArray

object ToArray extends ToArray; trait ToArray extends Predefs {
    class MadaRngToArray[A](_1: Expr[Rng[A]]) {
        def toArray = ToArrayExpr(_1).expr
    }
    implicit def toMadaRngToArray[A](_1: Expr[Rng[A]]): MadaRngToArray[A] = new MadaRngToArray[A](_1)
}

case class ToArrayExpr[A](_1: Expr[Rng[A]]) extends Expr[Array[A]] {
    override def _eval = _1 match {
        case FromArrayExpr(x1) => x1.eval
        case _ => ToArrayImpl(_1.xlazy)
    }
}

object ToArrayImpl {
    def apply[A](x: Expr[Rng[A]]): Array[A] = x.eval.traversal match {
        case _: ForwardTraversal => inForward(x)
        case _: SinglePassTraversal => inForward(x.force)
    }

    private def inForward[A](x: Expr[Rng[A]]): Array[A] = {
        val a = new Array[A](x.distance.eval.toInt)
        var i = 0
        x.foreach({ (e: A) => a(i) = e; i += 1 }).eval
        a
    }
}
