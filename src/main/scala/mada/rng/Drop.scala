
package mada.rng


// Drop

object Drop extends Drop

trait Drop extends Predefs {
    class MadaRngDrop[A](_1: Expr[Rng[A]]) {
        def drop(_2: Expr[Long]) = DropExpr(_1, _2).expr
    }
    implicit def toMadaRngDrop[A](_1: Expr[Rng[A]]) = new MadaRngDrop(_1)
}

case class DropExpr[A](_1: Expr[Rng[A]], _2: Expr[Long]) extends Expr[Rng[A]] {
    def eval = _1 match {
        case DropExpr(x1, x2) => DropImpl(x1.eval, x2.eval + _2.eval)
        case _ => DropImpl(_1.eval, _2.eval)
    }
}

object DropImpl {
    def apply[A](r: Rng[A], n: Long): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        r.traversal match {
            case _: RandomAccessTraversal => {
                p += java.lang.Math.min(q - p, n)
                p <=< q
            }
            case _: SinglePassTraversal => {
                var m = n
                while (m != 0 && p != q) { ++(p); m = m - 1 }
                p <=< q
            }
        }
    }
}


// DropWhile

object DropWhile extends DropWhile

trait DropWhile extends Predefs {
    class MadaRngDropWhile[A](_1: Expr[Rng[A]]) {
        def dropWhile(_2: Expr[A => Boolean]) = DropWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngDropWhile[A](_1: Expr[Rng[A]]) = new MadaRngDropWhile(_1)
}

case class DropWhileExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Rng[A]] {
    def eval = {
        val x1 = _1.toLazy
        val not2: A => Boolean = !_2.eval.apply(_)
        FindPointerOfExpr(x1, Expr(not2)).eval <=< x1.eval.end
    }
}
