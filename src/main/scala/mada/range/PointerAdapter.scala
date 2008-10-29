
package mada.range


abstract class PointerAdapter[From, To, P <: PointerAdapter[From, To, P]](protected val base: Pointer[From]) extends PointerFacade[To, P] {
    protected def __read: To
    protected def __write(e: To): Unit
    override def _read = __read
    override def _write(e: To) = __write(e)
    override def _traversalTag = base.traversalTag
    override def _equals(that: P) = base == that.base
    override def _increment = base++
    override def _hashCode = base.hashCode
    override def _decrement = base--
    override def _offset(d: Long) = base += d
    override def _difference(that: P) = base - that.base
}
