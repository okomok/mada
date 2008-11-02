
package mada.range


object ForEach {
    def apply[A](r: Range[A], f: A => Any) = {
        Accumulate(r, f, {(b: A => Any, a: A) => b(a); b})
        f
    }
}
