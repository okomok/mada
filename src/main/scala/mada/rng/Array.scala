
package mada.rng


//  Array[A] <-> Expr[Rng[A]]

object ArrayConversion extends ArrayConversion

trait ArrayConversion {
    implicit def toMadaArrayRngExpr[A](from: => Array[A]) = FromArrayExpr(Expr(from)).expr
    implicit def fromMadaArrayRngExpr[A](from: Expr[Rng[A]]) = ToArrayExpr(from).eval
}


// toRng

object ArrayToRng

trait ArrayToRng extends Predefs {
    class MadaRngArrayToRng[A](_1: Expr[Array[A]]) {
        def toRng = FromArrayExpr(_1).expr
    }
    implicit def toMadaRngArrayToRng[A](_1: Expr[Array[A]]) = new MadaRngArrayToRng(_1)
}

case class FromArrayExpr[A](_1: Expr[Array[A]]) extends Expr[Rng[A]] {
    override def eval = _1 match {
        case ToArrayExpr(a1) => a1.eval
        case _ => new ArrayRng(_1.eval)
    }
}

case class ArrayRng[A](base: Array[A]) extends IndexAccessRng[A] {
    override def _set(i: Long, e: A) { base(i.toInt) = e }
    override def _get(i: Long) = base(i.toInt)
    override def _size = base.length
}


// toArray

object ToArray extends ToArray

trait ToArray extends Predefs {
    class MadaRngToArray[A](_1: Expr[Rng[A]]) {
        def toArray = ToArrayExpr(_1).expr
    }
    implicit def toMadaRngToArray[A](_1: Expr[Rng[A]]) = new MadaRngToArray(_1)
}

case class ToArrayExpr[A](_1: Expr[Rng[A]]) extends Expr[Array[A]] {
    override def eval = _1 match {
        case FromArrayExpr(a1) => a1.eval
        case _ => ToArrayImpl(_1.toLazy)
    }
}

object ToArrayImpl {
    def apply[A](x: Expr[Rng[A]]): Array[A] = x.eval.traversal match {
        case _: ForwardTraversal => inForward(x)
        case _: SinglePassTraversal => inForward(CopyExpr(x))
    }

    private def inForward[A](x: Expr[Rng[A]]): Array[A] = {
        val a = new Array[A](DistanceExpr(x).eval.toInt)
        var i = 0
        ForeachExpr(x, Expr({(e: A) => a(i) = e; i = i + 1})).eval
        a
    }
}
