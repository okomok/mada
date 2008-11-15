
package mada.rng


// Find

object Find extends Find

trait Find extends Predefs {
    class MadaRngFind[A](_1: Expr[Rng[A]]) {
        def find(_2: Expr[A => Boolean]) = FindExpr(_1, _2).expr
    }
    implicit def toMadaRngFind[A](_1: Expr[Rng[A]]) = new MadaRngFind(_1)
}

case class FindExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Option[A]] {
    override def _eval = {
        val acc = Ref[Option[A]](None)
        val a2 = _2.eval
        // Prefer Loop to FindPointerOf so a fusion is enabled.
        LoopExpr(_1, Expr({ (e: A) => if (a2(e)) { acc := Some(e); false } else true })).eval
        acc.deref
    }
}


// FindPointerOf

object FindPointerOf extends FindPointerOf

trait FindPointerOf extends Predefs {
    class MadaRngFindPointerOf[A](_1: Expr[Rng[A]]) {
        def findPointerOf(_2: Expr[A => Boolean]) = FindPointerOfExpr(_1, _2).expr
    }
    implicit def toMadaRngFindPointerOf[A](_1: Expr[Rng[A]]) = new MadaRngFindPointerOf(_1)
}

case class FindPointerOfExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Pointer[A]] {
    override def _eval = FindPointerOfImpl(_1.eval, _2.eval)
}

object FindPointerOfImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Pointer[A] = {
        val (p, q) = (r.begin, r.end)
        while (p != q && !f(*(p))) { ++(p) }
        p
    }
}
