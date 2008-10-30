
package mada.range


class Size[E] extends (Range[E] => Long) {
    final def apply(r: Range[E]): Long = r.end - r.begin
}

object Size {
    final def apply[E](r: Range[E]) = (new Size[E])(r)
}
