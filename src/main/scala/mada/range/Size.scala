
package mada.range


object Size {
    def apply[E](r: Range[E]): Long = {
        Assert("requires RandomAccessRange", r.traversal conformsTo RandomAccessTraversal)
        r.end - r.begin
    }
}
