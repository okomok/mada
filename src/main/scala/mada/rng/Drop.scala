
package mada.rng


// Drop

object Drop extends Drop

trait Drop extends Predefs {
    class MadaRngDrop[A](_1: Expr[Rng[A]]) {
        def rng_drop(_2: Long) = DropExpr(_1, _2).expr
    }
    implicit def toMadaRngDrop[A](_1: Expr[Rng[A]]): MadaRngDrop[A] = new MadaRngDrop[A](_1)
}

case class DropExpr[A](_1: Expr[Rng[A]], _2: Long) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case DropExpr(x1, x2) => DropImpl(x1.eval, x2 + _2)
        case _ => DropImpl(_1.eval, _2)
    }
}

object DropImpl {
    def apply[A](r: Rng[A], n: Long): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        r.traversal match {
            case RandomAccessTraversal => {
                p += java.lang.Math.min(q - p, n)
                p <=< q
            }
            case SinglePassTraversal => {
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
        def rng_dropWhile(_2: A => Boolean) = DropWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngDropWhile[A](_1: Expr[Rng[A]]): MadaRngDropWhile[A] = new MadaRngDropWhile[A](_1)
}

case class DropWhileExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Rng[A]] {
    override def _eval = {
        val x1 = _1.toLazy
        val not2: A => Boolean = !_2(_)
        FindPointerOfExpr(x1, not2).eval <=< x1.eval.end
    }
}
