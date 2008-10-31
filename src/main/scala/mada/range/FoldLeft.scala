
package mada.range


case class FoldLeft[A, B](z: B, op: (B, A) => B) extends RangeFunction[B] {
    def apply[X <: A](r: Range[X]): B = {
        val p = r.begin; val q = r.end
        var acc = z
        while (p != q) {
            acc = op(acc, p.read)
            p++/
        }
        acc
    }
    override def fromRange[X] = FoldLeft[X, B](z, op.asInstanceOf[(B, X) => B]).apply[X](_)
}
