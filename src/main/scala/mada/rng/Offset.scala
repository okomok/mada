
package mada.rng


import Implies._
import Size._


object Offset extends Offset; trait Offset extends Predefs {
    class MadaRngOffset[A](_1: Expr.Of[Rng[A]]) {
        def offset(_2: Long, _3: Long) = OffsetExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngOffset[A](_1: Expr.Of[Rng[A]]): MadaRngOffset[A] = new MadaRngOffset[A](_1)
}


case class OffsetExpr[A](override val _1: Expr.Of[Rng[A]], _2: Long, _3: Long) extends Expr.Transform[Rng[A]] {
    override protected def _default = OffsetImpl(_1.eval, _2, _3)
}


object OffsetImpl {
    def apply[A](r: Rng[A], n1: Long, n2: Long): Rng[A] = {
        AssertModels(r, Traversal.Forward)
        Assert("too many offsets", (r models Traversal.RandomAccess) implies (n1 <= r./.size./ + n2))
        Assert("requires BidirectionalRng", (n1 < 0) implies (r models Traversal.Bidirectional))
        Assert("requires BidirectionalRng", (n2 < 0) implies (r models Traversal.Bidirectional))

        r.begin.advance(n1) <=< r.end.advance(n2)
    }
}
