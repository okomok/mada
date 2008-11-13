
package mada.rng


object Slice extends Slice

trait Slice {
    class MadaRngSlice[A](_1: Expr[Rng[A]]) {
        def drop(_2: Long, _3: Long) = SliceExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngSlice[A](_1: Expr[Rng[A]]) = new MadaRngSlice(_1)
}


case class SliceExpr[A](_1: Expr[Rng[A]], _2: Long, _3: Long) extends Expr[Rng[A]] {
    def eval = TakeExpr(DropExpr(_1, _2), _3 - _2).eval
}
