
package mada.range


object Size {
    def apply[A](r: Range[A]): Long = {
        Assert("requires RandomAccessRange", r.traversal conformsTo RandomAccessTraversal)
        r.end - r.begin
    }
}
