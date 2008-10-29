
package mada.range


class ArrayRange[E](val base: Array[E]) extends Range[E] {
    override def _begin = new ArrayPointer(base, 0)
    override def _end = new ArrayPointer(base, base.length)
}


class ArrayPointer[E](private val a: Array[E], private var i: Int)
    extends PointerAdapter[Long, E, ArrayPointer[E]](new NumberPointer(i)) {
    override def __read = a(base.read.toInt)
    override def __write(e: E) = a.update(i, e)
    override def _clone = new ArrayPointer(a, i)
}
