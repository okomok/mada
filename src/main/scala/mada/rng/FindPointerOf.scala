
package mada.rng


import Pointer._


object FindPointerOf extends FindPointerOf; trait FindPointerOf extends Predefs {
    class MadaRngFindPointerOf[A](_1: Expr[Rng[A]]) {
        def findPointerOf(_2: A => Boolean) = FindPointerOfExpr(_1, _2).expr
    }
    implicit def toMadaRngFindPointerOf[A](_1: Expr[Rng[A]]): MadaRngFindPointerOf[A] = new MadaRngFindPointerOf[A](_1)
}


case class FindPointerOfExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Pointer[A]] {
    override def _eval = FindPointerOfImpl(_1.eval, _2)
}


object FindPointerOfImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Pointer[A] = {
        val (p, q) = r.toPair
        while (p != q && !f(*(p))) { ++(p) }
        p
    }
}
