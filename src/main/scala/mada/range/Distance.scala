
package mada.range


object Distance {
    def apply[A](r: Range[A]): Long = {
        r.traversal match {
            case RandomAccessTraversal => r.size
            case SinglePassTraversal => r.foldLeft(0: Long, {(b: Long, a: A) => b + 1})
        }
    }
}
