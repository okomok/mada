
package mada.rng


object Window {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = {
        Assert("requires ForwardRng", r models ForwardTraversal)
        Assert("requires n <= m", n <= m)
        Assert("requires BidirectionalRng", Implies(n < 0, r models BidirectionalTraversal))

        val p = r.begin.advance(n)
        p <=< p.clone.advance(m - n)
    }
}
