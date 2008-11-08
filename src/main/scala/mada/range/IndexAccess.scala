
package mada.range


trait IndexAccess[A] {
    protected def _set(i: Long, e: A): Unit
    protected def _get(i: Long): A
    protected def _size: Long
    final def set(i: Long, e: A) = _set(i, e)
    final def get(i: Long) = _get(i)
    final def size = _size
}

case class ErrorNonWritableIndexAccess(val message: String) extends UnsupportedOperationException


object FromIndexAccess {
    def apply[A](ia: IndexAccess[A]): Range[A] = new IndexAccessRange(ia)
}

class IndexAccessRange[A](val indexAccess: IndexAccess[A])
        extends PointerRange[A](
            new IndexAccessPointer(indexAccess, 0),
            new IndexAccessPointer(indexAccess, indexAccess.size)) {
    override def size = indexAccess.size
}

class IndexAccessPointer[A](ia: IndexAccess[A], private var i: Long)
        extends PointerAdapter[Long, A, IndexAccessPointer[A]] {
    override val _base = new LongIntervalPointer(i)
    override def _read = ia.get(*(base))
    override def _write(e: A) = ia.set(*(base), e)
    override def _clone = new IndexAccessPointer(ia, *(base))
}
