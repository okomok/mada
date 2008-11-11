
package mada.rng


object Foreach {
    def apply[A, X](r: Rng[A], f: A => X): (A => X) = {
        val p = r.begin; val q = r.end
        while (p != q) {
            f(*(p))
            ++(p)
        }
        f
    }
}
