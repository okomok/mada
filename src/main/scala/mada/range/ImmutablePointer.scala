
package mada.range


class ImmutablePointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ImmutablePointer[A]] {
    override def _increment = { throw new ErrorImmutable(this) }
    override def _clone = { throw new ErrorImmutable(this) }
    override def _decrement =  { throw new ErrorImmutable(this) }
    override def _offset(d: Long) = { throw new ErrorImmutable(this) }
}

class ErrorImmutable[A](val pointer: Pointer[A]) extends UnsupportedOperationException
