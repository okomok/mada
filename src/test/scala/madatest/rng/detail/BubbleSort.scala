

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.Pointer._


object BubbleSort {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = {
        AssertModels(r, Traversal.Bidirectional)

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
