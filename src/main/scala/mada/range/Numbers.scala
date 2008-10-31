
package mada.range


object Numbers extends ((Long, Long) => Range[Long]) {
    def apply(n: Long, m: Long) = new NumberPointer(n) <=< new NumberPointer(m)
}

class NumberPointer(private var n: Long) extends PointerFacade[Long, NumberPointer] {
    override def _read = n
    override def _traversal = RandomAccessTraversal()
    override def _equals(that: NumberPointer) = n == that.n
    override def _increment = n = n + 1
    override def _clone = new NumberPointer(n)
    override def _hashCode = n.toInt // BUGBUG
    override def _decrement = n = n - 1
    override def _offset(d: Long) = n += d
    override def _difference(that: NumberPointer) = n - that.n
    final def base: Long = n
}
