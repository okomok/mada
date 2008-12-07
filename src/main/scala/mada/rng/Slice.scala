
package mada.rng


object Slice extends Slice; trait Slice extends Predefs {
    class MadaRngSlice[A](_1: ExprV2.Of[Rng[A]]) {
        def slice(_2: Long, _3: Long) = SliceExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngSlice[A](_1: ExprV2.Of[Rng[A]]): MadaRngSlice[A] = new MadaRngSlice[A](_1)
}


case class SliceExpr[A](_1: ExprV2.Of[Rng[A]], _2: Long, _3: Long) extends ExprV2.Adapter[Rng[A], Rng[A]] {
    override protected def _base = TakeExpr(DropExpr(_1, _2), _3 - _2)
}
