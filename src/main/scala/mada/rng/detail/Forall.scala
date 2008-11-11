
package mada.rng.detail


object Forall {
    def apply[A](r: Rng[A], f: A => Boolean): Boolean = r.find(!f(_)) == None
}
