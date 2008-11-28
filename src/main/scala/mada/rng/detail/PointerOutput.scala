
package mada.rng.detail


import Pointer._


object PointerOutput {
    def apply[A](p: Pointer[A], e: A): Pointer[A] = {
        *(p) = e
        ++(p)
    }
}
