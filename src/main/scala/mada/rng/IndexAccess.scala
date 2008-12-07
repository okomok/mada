
package mada.rng


trait IndexAccess[A] {
    def _set(startIndex: Long, e: A): Unit = { throw NotWritableIndexAccessError }
    def _get(startIndex: Long): A
    def _size: Long
    final def indexAccess = this
}


case class IndexAccessRngExpr[A](_1: IndexAccess[A]) extends ExprV2.ConstantOf[Rng[A]] {
    override def _of = new IndexAccessPointer(_1, 0) <=< new IndexAccessPointer(_1, _1._size)
}


class IndexAccessPointer[A](val indexAccess: IndexAccess[A], val startIndex: Long)
        extends PointerAdapter[Long, A, IndexAccessPointer[A]] {
    override val _base = new LongIntervalPointer(startIndex)

    override def _read = indexAccess._get(*(base))
    override def _write(e: A) = {
        try {
            indexAccess._set(*(base), e)
        } catch {
            case NotWritableIndexAccessError => throw new NotWritablePointerError(this)
        }
    }
    override def _copy = new IndexAccessPointer(indexAccess, *(base))

    override def toString = new StringBuilder().append("IndexAccessPointer(").append(*(base)).append(") of ").append(indexAccess).toString
}

object NotWritableIndexAccessError extends Error
