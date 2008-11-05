
package mada.range


object Numbers extends ((Long, Long) => Range[Long]) {
    def apply(n: Long, m: Long) = new NumberPointer(n) <=< new NumberPointer(m)
}

class NumberPointer(private var _base: Long) extends PointerFacade[Long, NumberPointer] {
    final def base = _base
    override def _read = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: NumberPointer) = _base == that._base
    override def _increment = _base = _base + 1
    override def _clone = new NumberPointer(_base)
    override def _hashCode = _base.toInt // BUGBUG
    override def _decrement = _base = _base - 1
    override def _offset(d: Long) = _base += d
    override def _difference(that: NumberPointer) = _base - that._base
}
