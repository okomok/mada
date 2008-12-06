
package mada.rng.detail


object ShallowToString {
    def apply[A](r: Rng[A]): String = {
        new StringBuilder().
            append("[").append(r.begin).append(", ").append(r.end).append(")").
        toString
    }
}
