
package mada.rng.detail


object Exists {
    def apply[A](r: Rng[A], f: A => Boolean): Boolean = r.find(f) != None
}
