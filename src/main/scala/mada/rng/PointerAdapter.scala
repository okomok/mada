
package mada.rng


trait PointerAdapter[From, To, P <: PointerAdapter[From, To, P]]
        extends PointerFacade[To, P] {
    protected def _base: Pointer[From]
    protected lazy val baseRef = new Ref(_base)
    final def base: Pointer[From] = baseRef.deref
    override def _read = *(base).asInstanceOf[To]
    override def _write(e: To) { *(base) = e.asInstanceOf[From] }
    override def _traversal = base.traversal
    override def _equals(that: P) = base == that.base
    override def _increment { base++/ }
    override def _hashCode = base.hashCode
    override def _decrement { base--/ }
    override def _offset(d: Long) { base += d }
    override def _difference(that: P) = base - that.base
}
