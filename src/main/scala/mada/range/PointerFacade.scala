
package mada.range


trait PointerFacade[E, P] extends Pointer[E] {
    protected def _equals(that: P): Boolean
    protected def _difference(that: P): Long = { throw ErrorNotRandomAccess(this) }
    protected def _compatible(that: P): Unit = { }

    override def equals(that: Any) = that match {
        case that: P => {
            _compatible(that)
            _equals(that)
        }
        case _ => false
    }

    override final def _difference_(that: Pointer[E]): Long = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _difference(p)
    }
}
