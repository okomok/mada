
package mada.range


class MonoAsRangeOf[From, To] extends (Range[From] => Range[To]) {
    override final def apply(r: Range[From]) = r >> Map((_: From).asInstanceOf[To])
}

case class AsRangeOf[To] extends RangeFunction[Range[To]] {
    override def fromRange[X] = new MonoAsRangeOf[X, To]
}
