
package mada.rng


trait IndexAccess[A] {
    def _set(startIndex: Long, e: A) { throw ErrorNotWritableIndexAccess }
    def _get(startIndex: Long): A
    def _size: Long
    final def indexAccess = this
}


case class IndexAccessRngExpr[A](_1: Expr[IndexAccess[A]]) extends Expr[Rng[A]] {
    override def _eval = {
        val ia = _1.eval
        new IndexAccessPointer(ia, 0) <=< new IndexAccessPointer(ia, ia._size)
    }
}


class IndexAccessPointer[A](val indexAccess: IndexAccess[A], val startIndex: Long)
        extends PointerAdapter[Long, A, IndexAccessPointer[A]] {
    override val _base = new LongIntervalPointer(startIndex)

    override def _read = indexAccess._get(*(base))
    override def _write(e: A) {
        try {
            indexAccess._set(*(base), e)
        } catch {
            case ErrorNotWritableIndexAccess => throw new ErrorNotWritable(this)
        }
    }
    override def _clone = new IndexAccessPointer(indexAccess, *(base))
}

object ErrorNotWritableIndexAccess extends UnsupportedOperationException
