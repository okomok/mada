
package mada.range.detail


object IsEmpty {
    def apply[A](r: Range[A]): Boolean = r.begin == r.end
}
