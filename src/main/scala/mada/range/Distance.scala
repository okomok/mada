
package mada.range


object Distance extends RangeFunction[Long] {
    def apply[E](r: Range[E]): Long = {
        val p = r.begin; val q = r.end
        var d: Long = 0
        r.traversal match {
            case RandomAccessTraversal() => q - p
            case _ => while (p != q) { p++/; d = d + 1 }
        }
        d
    }

    override def fromRange[E] = apply[E](_)
}
