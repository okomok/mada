
package mada.rng.detail


object IsEmpty {
    def apply[A](r: Rng[A]): Boolean = r.begin == r.end
}
