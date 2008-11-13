
package mada.rng


object Slice extends Slice

trait Slice {
    class MadaRngSlice[A](_1: Expr[Rng[A]]) {
        def drop(_2: Expr[Long], _3: Expr[Long]) = SliceExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngSlice[A](_1: Expr[Rng[A]]) = new MadaRngSlice(_1)
}


case class SliceExpr[A](_1: Expr[Rng[A]], _2: Expr[Long], _3: Expr[Long]) extends Expr[Rng[A]] {
    def eval = TakeExpr(DropExpr(_1, _2), Expr(_3.eval - _2.eval)).eval
}
