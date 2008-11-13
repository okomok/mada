
package mada.rng


// Drop

object Drop extends Drop

trait Drop {
    class MadaRngDrop[A](_1: Expr[Rng[A]]) {
        def drop(_2: Long) = DropExpr(_1, _2).expr
    }
    implicit def toMadaRngDrop[A](_1: Expr[Rng[A]]) = new MadaRngDrop(_1)
}

case class DropExpr[A](_1: Expr[Rng[A]], _2: Long) extends Expr[Rng[A]] {
    def eval = _1 match {
        case DropExpr(a1, a2) => DropImpl(a1.eval, a2 + _2)
        case _ => DropImpl(_1.eval, _2)
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

trait DropWhile {
    class MadaRngDropWhile[A](_1: Expr[Rng[A]]) {
        def dropWhile(_2: A => Boolean) = DropWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngDropWhile[A](_1: Expr[Rng[A]]) = new MadaRngDropWhile(_1)
}

case class DropWhileExpr[A](_1: Expr[Rng[A]], _2: A => Boolean)
        extends Expression[Rng[A]](DropWhileImpl(_1.eval, _2))

object DropWhileImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        FindPointerOfImpl(r, !f(_: A)) <=< r.end
    }
}
