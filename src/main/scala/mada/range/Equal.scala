
package mada.range


object Equal {
    def apply[E1, E2](r1: Range[E1], p2: Pointer[E2], f: (E1, E2) => Boolean): Boolean = {
        val p1 = r1.begin; val q1 = r1.end
        while (p1 != q1) {
            if (!f(*(p1), *(p2)))
                return false
            p1++/; p2++/
        }
        true
    }

    def apply[E](r1: Range[E], p2: Pointer[E]) = apply[E, E](r1, p2, _ == _)
}
