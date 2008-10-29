
package mada.range


abstract class PointerAdapter[From, To, P <: PointerAdapter[From, To, P]](protected val base: Pointer[From])
extends PointerFacade[To, P] {
    override def _read = base.read.asInstanceOf[To]
    override def _write(e: To) = base.write(e.asInstanceOf[From])
    override def _traversalTag = base.traversalTag
    override def _equals(that: P) = base == that.base
    override def _increment = base.pre_++
    override def _hashCode = base.hashCode
    override def _decrement = base.pre_--
    override def _offset(d: Long) = base += d
    override def _difference(that: P) = base - that.base
}
