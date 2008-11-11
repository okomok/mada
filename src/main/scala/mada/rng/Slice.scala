
package mada.rng


object Slice {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = r.drop(n).take(m - n)
}
