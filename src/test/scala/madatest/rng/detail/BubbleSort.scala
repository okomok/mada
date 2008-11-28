
package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.Pointer._


object BubbleSort {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = {
        AssertModels(r, BidirectionalTraversal)

        val (p, q) = r.toPair

        var swapped = false
        do {
            val curr = p.copy
            val next = curr.copy
            ++(next)
            swapped = false
            while (next != q) {
                if (f(*(next), *(curr))) {
                    curr swap next
                    swapped = true
                }
                ++(curr); ++(next)
            }
            --(q)
        } while (swapped)
    }

    def apply[A <% Ordered[A]](r: Rng[A]): Unit = apply(r, (_: A) < (_: A))
}
