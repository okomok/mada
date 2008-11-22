
package mada.rng.detail


object PointerOutput {
    def apply[A](p: Pointer[A], e: A): Pointer[A] = {
        *(p) = e; ++(p)
    }
}
