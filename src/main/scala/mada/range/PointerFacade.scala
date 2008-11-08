
package mada.range


trait PointerFacade[E, P] extends Pointer[E] {
    protected def _equals(that: P): Boolean
    protected def _difference(that: P): Long = { throw new ErrorNotRandomAccess(this) }
    protected def _compatible(that: P): Unit = { }

    override final def equals(that: Any) = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _equals(p)
    }

    override final def _difference_(that: Pointer[E]): Long = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _difference(p)
    }
}
