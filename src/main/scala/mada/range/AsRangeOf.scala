
package mada.range


case class AsRangeOf[To] extends RangeFunction[Range[To]] {
    def apply[From](r: Range[From]): Range[To] = r->Map((_: From).asInstanceOf[To])
    override def fromRange[X] = apply[X](_)
}
