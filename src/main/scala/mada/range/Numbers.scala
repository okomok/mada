
package mada.range


class Numbers(private val n: Long, private val m: Long) extends Range[Long] {
    override def _begin = new NumberPointer(n)
    override def _end = new NumberPointer(m)
}


class NumberPointer(private var n: Long) extends PointerFacade[Long, NumberPointer] {
    override def _read = n
    override def _traversalTag = RandomAccessTraversalTag()
    override def _equals(that: NumberPointer) = n == that.n
    override def _increment = n = n + 1
    override def _clone = new NumberPointer(n)
    override def _hashCode = n.toInt
    override def _decrement = n = n - 1
    override def _offset(d: Long) = n += d
    override def _difference(that: NumberPointer) = n - that.n
    final def base: Long = n
}
