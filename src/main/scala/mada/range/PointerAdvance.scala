
package mada.range


object PointerAdvance {
    def apply[A](p: Pointer[A], d_ : Long): Pointer[A] = {
        var d = d_
        p.traversal match {
            case RandomAccessTraversal => p += d
            case BidirectionalTraversal => {
                if (d >= 0)
                    while (d != 0) { ++(p); d = d - 1 }
                else
                    while (d != 0) { --(p); d = d + 1 }
            }
            case _ => while (d != 0) { ++(p); d = d - 1 }
        }
        p
    }
}
