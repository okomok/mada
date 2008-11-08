
package mada.range


object Foreach {
    def apply[A, X](r: Range[A], f: A => X): (A => X) = {
        r.foldLeft(f, {(b: A => X, a: A) => b(a); b})
        f
    }
}
