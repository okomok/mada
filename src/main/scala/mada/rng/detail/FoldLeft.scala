
package mada.rng.detail


object FoldLeft {
    def apply[A, B](r: Rng[A], z: B, op: (B, A) => B): B = {
        val p = r.begin; val q = r.end
        var acc = z
        while (p != q) {
            acc = op(acc, *(p))
            ++(p)
        }
        acc
    }
}
