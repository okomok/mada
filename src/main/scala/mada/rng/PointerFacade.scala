
package mada.rng


trait PointerFacade[A, P] extends Pointer[A] with PointerPre_* {
    protected def _equals(that: P): Boolean = { throw new ErrorNotSinglePass(this) }
    protected def _difference(that: P): Long = { throw new ErrorNotRandomAccess(this) }
    protected def _compatible(that: P) { }

    override final def _equals_(that: Pointer[A]) = {
        val p = that.plain.asInstanceOf[P]
        _compatible(p)
        _equals(p)
    }

    override final def _difference_(that: Pointer[A]): Long = {
        val p = that.plain.asInstanceOf[P]
        _compatible(p)
        _difference(p)
    }
}
