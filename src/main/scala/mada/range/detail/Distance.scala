
package mada.range.detail


object Distance {
    def apply[A](r: Range[A]): Long = r.traversal match {
        case _: RandomAccessTraversal => r.size
        case _: SinglePassTraversal => r.foldLeft(0: Long, {(b: Long, a: A) => b + 1})
    }
}
