
package mada.range


object Find {
    def apply[A](r: Range[A], f: A => Boolean): Option[A] = {
        val q = r.end
        val p = FindPointerOf(r.begin, q, f)
        if (p != q) Some(*(p)) else None
    }
}


object FindPointerOf {
    def apply[A](r: Range[A], f: A => Boolean): Pointer[A] = apply(r.begin, r.end, f)

    def apply[A](p: Pointer[A], q: Pointer[A], f: A => Boolean): Pointer[A] = {
        while (p != q && !f(*(p))) { ++(p) }
        p
    }
}
