
package mada.range


object Foreach {
    def apply[A, X](r: Range[A], f: A => X): (A => X) = {
        val p = r.begin; val q = r.end
        while (p != q) {
            f(*(p))
            ++(p)
        }
        f
    }
}
