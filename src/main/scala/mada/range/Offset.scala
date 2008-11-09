
package mada.range


object Offset {
    def apply[A](r: Range[A], n1: Long, n2: Long): Range[A] = {
        Assert("requires ForwardRange", r models ForwardTraversal)
        Assert("too many offsets", Implies(r models RandomAccessTraversal, n1 <= r.size + n2))
        Assert("requires BidirectionalRange", Implies(n1 < 0, r models BidirectionalTraversal))
        Assert("requires BidirectionalRange", Implies(n2 < 0, r models BidirectionalTraversal))

        r.begin.advance(n1) <=< r.end.advance(n2)
    }
}
