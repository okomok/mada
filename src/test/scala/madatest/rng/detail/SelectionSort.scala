
package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.Pointer._


object SelectionSort {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Unit = {
        AssertModels(r, ForwardTraversal)

        val (p, q) = r.toPair

        while (p != q) {
            val mid = MinElement(p <=< q, f)
            if (p != mid) { p swap mid }
            ++(p)
        }
    }

    def apply[A <% Ordered[A]](r: Rng[A]): Unit = apply(r, (_: A) < (_: A))
}


object MinElement {
    def apply[A](r: Rng[A], f: (A, A) => Boolean): Pointer[A] = {
        val (p, q) = r.toPair

        if (p == q)
            return p

        var t = p.copy
        while (++(p) != q) {
            if (f(*(p), *(t))) { t = p.copy }
        }

        t
    }
}
