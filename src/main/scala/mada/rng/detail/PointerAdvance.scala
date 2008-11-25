
package mada.rng.detail


object PointerAdvance {
    def apply[A](p: Pointer[A], dd : Long): Pointer[A] = {
        var d = dd
        p.traversal match {
            case RandomAccessTraversal => p += d
            case BidirectionalTraversal => {
                if (d >= 0)
                    while (d != 0) { ++(p); d = d - 1 }
                else
                    while (d != 0) { --(p); d = d + 1 }
            }
            case SinglePassTraversal => while (d != 0) { ++(p); d = d - 1 }
        }
        p
    }
}
