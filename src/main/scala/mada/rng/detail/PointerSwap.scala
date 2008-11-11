
package mada.rng.detail


object PointerSwap {
    def apply[A](p: Pointer[A], q: Pointer[A]) {
        val tmp = *(p)
        *(p) = *(q)
        *(q) = tmp
    }
}
