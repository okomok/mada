
package mada.range


object Slice {
    def apply[A](r: Range[A], n: Long, m: Long): Range[A] = r.drop(n).take(m - n)
}
