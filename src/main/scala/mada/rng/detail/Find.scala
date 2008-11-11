
package mada.rng.detail


object Find {
    def apply[A](r: Rng[A], f: A => Boolean): Option[A] = {
        val p = FindPointerOf(r, f)
        val q = r.end
        if (p != q) Some(*(p)) else None
    }
}

object FindPointerOf {
    def apply[A](r: Rng[A], f: A => Boolean): Pointer[A] = {
        val p = r.begin; val q = r.end
        while (p != q && !f(*(p))) { ++(p) }
        p
    }
}
