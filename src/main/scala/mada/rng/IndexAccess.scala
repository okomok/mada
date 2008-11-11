
package mada.rng


trait IndexAccessRng[A] extends Rng[A] {
    def _set(i: Long, e: A) { throw new ErrorNotWritableIndexAccess }
    def _get(i: Long): A
    def _size: Long

    override lazy val _begin = new IndexAccessPointer(this, 0)
    override def _end = new IndexAccessPointer(this, size)
    override def distance = _size
    override def size = _size
}

class IndexAccessPointer[A](ia: IndexAccessRng[A], private var i: Long)
        extends PointerAdapter[Long, A, IndexAccessPointer[A]] {
    override val _base = new LongIntervalPointer(i)

    override def _read = ia._get(*(base))
    override def _write(e: A) {
        try {
            ia._set(*(base), e)
        } catch {
            case _: ErrorNotWritableIndexAccess => throw new ErrorNotWritable(this)
        }
    }
    override def _clone = new IndexAccessPointer(ia, *(base))
}

class ErrorNotWritableIndexAccess extends UnsupportedOperationException
