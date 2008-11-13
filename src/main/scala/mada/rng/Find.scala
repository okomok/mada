
package mada.rng


// Find

object Find extends Find

trait Find {
    class MadaRngFind[A](_1: Expr[Rng[A]]) {
        def find(_2: Expr[A => Boolean]) = FindExpr(_1, _2).expr
    }
    implicit def toMadaRngFind[A](_1: Expr[Rng[A]]) = new MadaRngFind(_1)
}

case class FindExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Option[A]] {
    def eval = FindImpl(_1.eval, _2.eval)
}

object FindImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Option[A] = {
        val (p, q) = (FindPointerOfImpl(r, f), r.end)
        if (p != q) Some(*(p)) else None
    }
}


// FindPointerOf

object FindPointerOf extends FindPointerOf

trait FindPointerOf {
    class MadaRngFindPointerOf[A](_1: Expr[Rng[A]]) {
        def findPointerOf(_2: Expr[A => Boolean]) = FindPointerOfExpr(_1, _2).expr
    }
    implicit def toMadaRngFindPointerOf[A](_1: Expr[Rng[A]]) = new MadaRngFindPointerOf(_1)
}

case class FindPointerOfExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Pointer[A]] {
    def eval = FindPointerOfImpl(_1.eval, _2.eval)
}

object FindPointerOfImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Pointer[A] = {
        val (p, q) = (r.begin, r.end)
        while (p != q && !f(*(p))) { ++(p) }
        p
    }
}
