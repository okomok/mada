
package mada.rng.detail


object CopyTo {
    def apply[A, B >: A](r1: Rng[A], p2: Pointer[B]): Pointer[B] = {
        r1.foreach(p2.output)
        p2
    }
}
