
package mada.range.detail


object Mismatch {
    def apply[A, B](r1: Range[A], p2: Pointer[B], f: (A, B) => Boolean): (Pointer[A], Pointer[B]) = {
        val p1 = r1.begin; val q1 = r1.end
        while (p1 != q1 && f(*(p1), *(p2))) {
            ++(p1); ++(p2)
        }
        (p1, p2)
    }

    def apply[E](r1: Range[E], p2: Pointer[E]) = apply[E, E](r1, p2, _ == _)
}
