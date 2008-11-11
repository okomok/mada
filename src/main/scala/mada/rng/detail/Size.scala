
package mada.rng.detail


object Size {
    def apply[A](r: Rng[A]): Long = {
        Assert("requires RandomAccessRng", r models RandomAccessTraversal)
        r.end - r.begin
    }
}
