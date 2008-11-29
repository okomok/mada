
package mada.rng


class ImmutablePointer[A](val base: Pointer[A]) extends Pointer[A] {
    override def _read = base.read
    override def _write(e: A) = base.write(e)
    override def _traversal = base.traversal
    override def _equals_(that: Pointer[A]): Boolean = this.plain == that.plain
    override def _increment { throw new ErrorNotMutable(this) }
    override def _copy = this
    override def _decrement { throw new ErrorNotMutable(this) }
    override def _offset(d: Long) { throw new ErrorNotMutable(this) }
    override def _difference_(that: Pointer[A]): Long = this.plain - that.plain

    override def plain = base.plain
    override def immutable = this
}
