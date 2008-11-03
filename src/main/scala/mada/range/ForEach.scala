
package mada.range


object ForEach {
    def apply[A, X](r: Range[A], f: A => X): (A => X) = {
        r.accumulate(f, {(b: A => X, a: A) => b(a); b})
        f
    }
}
