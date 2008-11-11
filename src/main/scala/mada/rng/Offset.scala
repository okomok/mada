
package mada.rng


object Offset {
    def apply[A](r: Rng[A], n1: Long, n2: Long): Rng[A] = {
        Assert("requires ForwardRng", r models ForwardTraversal)
        Assert("too many offsets", Implies(r models RandomAccessTraversal, n1 <= r.size + n2))
        Assert("requires BidirectionalRng", Implies(n1 < 0, r models BidirectionalTraversal))
        Assert("requires BidirectionalRng", Implies(n2 < 0, r models BidirectionalTraversal))

        r.begin.advance(n1) <=< r.end.advance(n2)
    }
}
