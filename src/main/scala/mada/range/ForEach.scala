
package mada.range


object ForEach {
    def apply[A, X](r: Range[A], f: A => X) = {
        Accumulate(r, f, {(b: A => Any, a: A) => b(a); b})
        f
    }
}
