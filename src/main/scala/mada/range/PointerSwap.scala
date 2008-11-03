
package mada.range


object PointerSwap {
    def apply[A](p: Pointer[A], q: Pointer[A]): Unit = {
        val tmp = *(p)
        *(p) = *(q)
        *(q) = tmp
    }
}
