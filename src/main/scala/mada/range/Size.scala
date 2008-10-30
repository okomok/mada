
package mada.range


object Size extends RangeFunction[Long] {
    def apply[E](r: Range[E]): Long = r.end - r.begin
    override def fromRange[E] = apply[E](_)
}
