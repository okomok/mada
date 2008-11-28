
package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.Pointer._


object CombSort {
    def apply[A](p: Pointer[A], q: Pointer[A], f: (A, A) => Boolean): Unit = {
        Assert("requires RandomAccessPointer", p.traversal <:< RandomAccessTraversal)

        var gap = q - p
        if (gap < 1L)
            return

        val p2 = q.copy
        var swapped = false
        do {
            var newgap = (gap * 10L + 3L) / 13L
            if (newgap < 1L)
                newgap = 1L

            p2 += (newgap - gap) // paren is needed!
            gap = newgap
            swapped = false

            val (target1, target2) = (p.copy, p2.copy)
            while (target2 != q) {
                if (f(*(target2), *(target1))) {
                    target1 swap target2
                    swapped = true
                }
                ++(target1); ++(target2)
            }
        } while ((gap > 1L) || swapped)
    }

    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = apply(r.begin, r.end, f)
    def apply[A <% Ordered[A]](p: Pointer[A], q: Pointer[A]): Unit = apply(p, q, (_: A) < (_: A))
    def apply[A <% Ordered[A]](r: Rng[A]): Unit = apply(r, (_: A) < (_: A))
}
