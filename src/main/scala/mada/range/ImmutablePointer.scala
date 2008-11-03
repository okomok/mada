
package mada.range


class ImmutablePointer[A](private val p: Pointer[A])
        extends PointerAdapter[A, A, ImmutablePointer[A]](p) {
    override def _increment = { throw ErrorImmutable(this) }
    override def _clone = { throw ErrorImmutable(this) }
    override def _decrement =  { throw ErrorImmutable(this) }
    override def _offset(d: Long) = { throw ErrorImmutable(this) }
    override def toImmutable = this
}

case class ErrorImmutable[A](pointer: Pointer[A]) extends UnsupportedOperationException
