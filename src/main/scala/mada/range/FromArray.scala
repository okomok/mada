
package mada.range


object FromArray {
    def apply[A](a: Array[A]): ArrayRange[A] = new ArrayRange(a)

    def apply[A](es: A*): ArrayRange[A] = {
        val a = new Array[A](es.length)
        var i = 0
        for (e <- es.elements) {
            a(i) = e
            i = i + 1
        }
        apply(a)
    }
}

class ArrayRange[A](val array: Array[A])
        extends PointerRange[A](new ArrayPointer(array, 0), new ArrayPointer(array, array.length)) {
    override def size = array.length
}

class ArrayPointer[A](private val a: Array[A], private var i: Int)
		extends PointerAdapter[Long, A, ArrayPointer[A]] {
    override val _base = new NumberPointer(i)
    override def _read = a(derefBase)
    override def _write(e: A) = a(derefBase) = e
    override def _clone = new ArrayPointer(a, derefBase)
    private def derefBase: Int = *(base).toInt
}
