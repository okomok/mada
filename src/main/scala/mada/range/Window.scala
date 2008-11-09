
package mada.range


object Window {
    def apply[A](r: Range[A], n: Long, m: Long): Range[A] = {
        Assert("requires ForwardRange", r models ForwardTraversal)
        Assert("requires n <= m", n <= m)
        Assert("requires BidirectionalRange", Implies(n < 0, r models BidirectionalTraversal))

        val p = r.begin.advance(n)
        p <=< p.clone.advance(m - n)
    }
}
