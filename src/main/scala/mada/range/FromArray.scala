
package mada.range


object FromArray {
    def apply[E](a: Array[E]) = new ArrayPointer(a, 0) <=< new ArrayPointer(a, a.length)
}


class ArrayPointer[E](private val a: Array[E], private var i: Int)
    extends PointerAdapter[Long, E, ArrayPointer[E]](new NumberPointer(i)) {
    override def _read = a(base.read.toInt)
    override def _write(e: E) = a.update(i, e)
    override def _clone = new ArrayPointer(a, i)
}
