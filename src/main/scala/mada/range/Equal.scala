
package mada.range


object Equal {
    def apply[E1, E2](r1: Range[E1], p2: Pointer[E2], f: (E1, E2) => Boolean): Boolean = r1.mismatch(p2, f)._1 == r1.end
    def apply[E](r1: Range[E], p2: Pointer[E]) = apply[E, E](r1, p2, _ == _)
}
