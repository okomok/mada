
package mada.rng.detail


object First {
    def apply[A](r: Rng[A]): A = {
        Assert("out of Rng", !r.isEmpty)
        *(r.begin)
    }
}

object Last {
    def apply[A](r: Rng[A]): A = {
        Assert("out of Rng", !r.isEmpty)
        Assert("requires BidirectionalRng", r models BidirectionalTraversal)
        *(--(r.end))
    }
}
