
package mada.rng


// Jcl <-> Expr[Rng[A]]

object JclConversions extends JclConversions

trait JclConversions {
    implicit def toMadaArrayListRngExpr[A](from: => java.util.ArrayList[A]) = FromArrayListExpr(Expr(from)).expr
//    implicit def fromMadaArrayListRngExpr[A](from: Expr[Rng[A]]) = ToArrayListExpr(from).eval
}


// Expr[Jcl[A]] -> Expr[Rng[A]]

trait JclToRng
        extends ArrayListToRng


// toRng

trait ArrayListToRng extends Predefs {
    class MadaRngArrayListToRng[A](_1: Expr[java.util.ArrayList[A]]) {
        def toRng = FromArrayListExpr(_1).expr
    }
    implicit def toMadaRngArrayListToRng[A](_1: Expr[java.util.ArrayList[A]]) = new MadaRngArrayListToRng(_1)
}

case class FromArrayListExpr[A](_1: Expr[java.util.ArrayList[A]]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case ToArrayListExpr(x1) => x1.eval
        case _ => new ArrayListRng(_1.eval)
    }
}

class ArrayListRng[A](a: java.util.ArrayList[A]) extends IndexAccessRng[A] {
    override def _set(i: Long, e: A) { a.set(i.toInt, e) }
    override def _get(i: Long) = a.get(i.toInt)
    override def _size = a.size
}


// toArrayList

object ToArrayList extends ToArrayList

trait ToArrayList extends Predefs {
    class MadaRngToArrayList[A](_1: Expr[Rng[A]]) {
        def toArrayList = ToArrayListExpr(_1).expr
    }
    implicit def toMadaRngToArrayList[A](_1: Expr[Rng[A]]) = new MadaRngToArrayList(_1)
}

case class ToArrayListExpr[A](_1: Expr[Rng[A]]) extends Expr[java.util.ArrayList[A]] {
    override def _eval = _1 match {
        case FromArrayListExpr(x1) => x1.eval
        case _ => ToArrayListImpl(_1.toLazy)
    }
}

object ToArrayListImpl {
    def apply[A](x: Expr[Rng[A]]): java.util.ArrayList[A] = {
        var a = newArrayList(x)
        ForeachExpr(x, Expr({(e: A) => a.add(e)}))
        a
    }

    private def newArrayList[A](x: Expr[Rng[A]]) = x.eval.traversal match {
        case _: RandomAccessTraversal => new java.util.ArrayList[A](SizeExpr(x).eval.toInt)
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
