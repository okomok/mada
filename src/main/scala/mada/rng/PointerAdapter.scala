

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


trait PointerAdapter[From, To, P <: PointerAdapter[From, To, P]]
        extends PointerFacade[To, P] {
    protected def _base: Pointer[From]
    protected lazy val baseRef = new Ref(_base)
    final def base: Pointer[From] = baseRef.deref
    override protected def _read = *(base).asInstanceOf[To]
    override protected def _write(e: To) = { *(base) = e.asInstanceOf[From] }
    override protected def _traversal = base.traversal
    override protected def _equals(that: P) = base == that.base
    override protected def _increment = { base.pre_++ }
    override protected def _decrement = { base.pre_-- }
    override protected def _offset(d: Long) = { base += d }
    override protected def _difference(that: P) = base - that.base
    override def hashCode = base.hashCode
}
