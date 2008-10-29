
package mada.range


class PointerAdapter[E, P <: PointerAdapter[E, P]](protected val base: Pointer[E]) extends PointerFacade[E, P] {
    override def _traversalTag = base.traversalTag
    override def _read = base.read
    override def _write(e: E) = base.write(e)
    override def _equals(that: P) = base == that.base
    override def _increment = base++
    override def _hashCode = base.hashCode
    override def _decrement = base--
    override def _offset(d: Long) = base += d
    override def _difference(that: P) = base - that.base
}
