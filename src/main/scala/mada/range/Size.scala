
package mada.range


class Size[E] extends (Range[E] => Long) {
    final def apply(r: Range[E]): Long = r.end - r.begin
}

object Size extends RangeFunction[Long] {
    final def apply[E](r: Range[E]) = (new Size[E])(r)
    override def fromRange[E] = new Size[E]
}
