
package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.Pointer._


object SelectionSort {
    def apply[A](p: Pointer[A], q: Pointer[A], f: (A, A) => Boolean): Unit = {
        Assert("requires ForwardPointer", p.traversal <:< ForwardTraversal)

        while (p != q) {
            val mid = MinElement(p, q, f)
            if (p != mid) { p swap mid }
            ++(p)
        }
    }

    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = apply(r.begin, r.end, f)
    def apply[A <% Ordered[A]](p: Pointer[A], q: Pointer[A]): Unit = apply(p, q, (_: A) < (_: A))
    def apply[A <% Ordered[A]](r: Rng[A]): Unit = apply(r, (_: A) < (_: A))
}


object MinElement {
    def apply[A](pp: Pointer[A], q: Pointer[A], f: (A, A) => Boolean): Pointer[A] = {
        val p = pp.copy

        if (p == q)
            return p

        var t = p.copy
        while (++(p) != q) {
            if (f(*(p), *(t))) { t = p.copy }
        }

        t
    }
}
