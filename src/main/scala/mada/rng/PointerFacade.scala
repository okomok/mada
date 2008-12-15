

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


trait PointerFacade[A, P] extends Pointer[A] with PointerPre_* {
    protected def _equals(that: P): Boolean = {
        Assert("must be overridden", false)
        throw new Error()
    }
    protected def _difference(that: P): Long = {
        Assert("must be overridden", false)
        throw new Error()
    }
    protected def _compatible(that: P): Unit = { }

    override final def _equals_(that: Pointer[A]) = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _equals(p)
    }

    override final def _difference_(that: Pointer[A]): Long = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _difference(p)
    }
}
