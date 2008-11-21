
package mada.rng


object Offset extends Offset

trait Offset extends Predefs {
    class MadaRngOffset[A](_1: Expr[Rng[A]]) {
        def rng_offset(_2: Expr[Long], _3: Expr[Long]) = OffsetExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngOffset[A](_1: Expr[Rng[A]]): MadaRngOffset[A] = new MadaRngOffset[A](_1)
}


case class OffsetExpr[A](_1: Expr[Rng[A]], _2: Expr[Long], _3: Expr[Long]) extends Expr[Rng[A]] {
    override def _eval = OffsetImpl(_1.eval, _2.eval, _3.eval)
}


object OffsetImpl {
    def apply[A](r: Rng[A], n1: Long, n2: Long): Rng[A] = {
        AssertModels(r, ForwardTraversal)
        Assert("too many offsets", Implies(r models RandomAccessTraversal, n1 <= SizeExpr(Expr(r)).eval + n2))
        Assert("requires BidirectionalRng", Implies(n1 < 0, r models BidirectionalTraversal))
        Assert("requires BidirectionalRng", Implies(n2 < 0, r models BidirectionalTraversal))

        r.begin.advance(n1) <=< r.end.advance(n2)
    }
}
