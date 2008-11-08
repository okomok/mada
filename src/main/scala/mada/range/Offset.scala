
package mada.range


object Offset {
    def apply[A](r: Range[A], n1: Long, n2: Long): Range[A] = {
        Assert("requires ForwardRange", r in ForwardTraversal)
        Assert("too many offsets", Implies(r in RandomAccessTraversal, n1 <= r.size + n2))
        Assert("requires BidirectionalRange", Implies(n1 < 0, r in BidirectionalTraversal))
        Assert("requires BidirectionalRange", Implies(n1 < 0, r in BidirectionalTraversal))

        r.begin.advance(n1) <=< r.end.advance(n2)
    }
}
