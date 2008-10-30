
package mada.range


trait PointerFacade[E, P] extends Pointer[E] {
    protected def _equals(that: P): Boolean
    protected def _difference(that: P): Long = { throw NotRandomAccess(this) }
    override final def equals(that: Any): Boolean = that match {
        case that: P => this._equals(that)
        case _ => false
    }
    override final def _difference_(that: Pointer[E]): Long = _difference(that.asInstanceOf[P])
}
