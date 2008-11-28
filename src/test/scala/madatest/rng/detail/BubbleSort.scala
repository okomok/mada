
package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.Pointer._


object BubbleSort {
    def apply[A](p: Pointer[A], q: Pointer[A], f: (A, A) => Boolean): Unit = {
        Assert("requires BidirectionalPointer", p.traversal <:< BidirectionalTraversal)

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

    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = apply(r.begin, r.end, f)
    def apply[A <% Ordered[A]](p: Pointer[A], q: Pointer[A]): Unit = apply(p, q, (_: A) < (_: A))
    def apply[A <% Ordered[A]](r: Rng[A]): Unit = apply(r, (_: A) < (_: A))
}
