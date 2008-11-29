
package mada.rng


import Implies._


object Offset extends Offset; trait Offset extends Predefs {
    class MadaRngOffset[A](_1: Expr[Rng[A]]) {
        def rng_offset(_2: Long, _3: Long) = OffsetExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngOffset[A](_1: Expr[Rng[A]]): MadaRngOffset[A] = new MadaRngOffset[A](_1)
}


case class OffsetExpr[A](_1: Expr[Rng[A]], _2: Long, _3: Long) extends Expr[Rng[A]] {
    override def _eval = OffsetImpl(_1.eval, _2, _3)
}


object OffsetImpl {
    def apply[A](r: Rng[A], n1: Long, n2: Long): Rng[A] = {
        AssertModels(r, ForwardTraversal)
        Assert("too many offsets", (r models RandomAccessTraversal) implies (n1 <= SizeExpr(Expr(r)).eval + n2))
        Assert("requires BidirectionalRng", (n1 < 0) implies (r models BidirectionalTraversal))
        Assert("requires BidirectionalRng", (n2 < 0) implies ( r models BidirectionalTraversal))

        r.begin.advance(n1) <=< r.end.advance(n2)
    }
}
