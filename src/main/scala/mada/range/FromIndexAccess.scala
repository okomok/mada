
package mada.range


trait IndexAccess[A] {
    protected def _set(i: Long, e: A): Unit
    protected def _get(i: Long): A
    protected def _length: Long
    final def set(i: Long, e: A) = _set(i, e)
    final def get(i: Long) = _get(i)
    final def length = _length
}

case class ErrorNonWritableIndexAccess(message: String) extends UnsupportedOperationException


object FromIndexAccess {
    def apply[A](ia: IndexAccess[A]): Range[A] = new FromIndexAccessRange(ia)
}

class FromIndexAccessRange[A](val indexAccess: IndexAccess[A])
        extends PointerRange[A](
            new FromIndexAccessPointer(indexAccess, 0),
            new FromIndexAccessPointer(indexAccess, indexAccess.length)) {
    override def size = indexAccess.length
}

class FromIndexAccessPointer[A](private val ia: IndexAccess[A], private var i: Long)
        extends PointerAdapter[Long, A, FromIndexAccessPointer[A]] {
    override val _base = new NumberPointer(i)
    override def _read = ia.get(*(base))
    override def _write(e: A) = ia.set(*(base), e)
    override def _clone = new FromIndexAccessPointer(ia, *(base))
}
