

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import PointerAdvance._


object Pointer extends Namespace
        with PointerOrdered
        with PointerPreOps


trait Pointer[A] {
// element-access
    protected def _read: A = throw new NotReadablePointerError(this)
    protected def _write(e: A): Unit = throw new NotWritablePointerError(this)
    final def read: A = _read
    final def write(e: A): Pointer[A] = { _write(e); this }

// traversal
    protected def _traversal: Traversal
    final def traversal = _traversal

// single-pass
    protected def _increment: Unit = {
        Assert("must be overridden", false)
        throw new Error()
    }
    protected def _equals_(that: Pointer[A]): Boolean
    override final def equals(that: Any): Boolean = _equals_(that.asInstanceOf[Pointer[A]])
    final def pre_++ : Pointer[A] = {
        Assert("must be SinglePass", traversal <:< Traversal.SinglePass)
        _increment; this
    }

// forward
    protected def _copy: Pointer[A] = {
        Assert("must be overridden", false)
        throw new Error()
    }
    final def copy: Pointer[A] = {
        Assert("must be Forward", traversal <:< Traversal.Forward)
        _copy
    }
    final def ++ : Pointer[A] = { val tmp = copy; pre_++; tmp }

// bidirectional
    protected def _decrement: Unit = {
        Assert("must be overridden", false)
        throw new Error()
    }
    final def pre_-- : Pointer[A] = {
        Assert("must be Bidirectional", traversal <:< Traversal.Bidirectional)
        _decrement; this
    }
    final def -- : Pointer[A] = { val tmp = copy; pre_--; tmp }

// random-access
    protected def _offset(d: Long): Unit = {
        Assert("must be overridden", false)
        throw new Error()
    }
    protected def _difference_(that: Pointer[A]): Long = {
        Assert("must be overridden", false)
        throw new Error()
    }
    final def - (that: Pointer[A]): Long = {
        Assert("must be RandomAccess", traversal <:< Traversal.RandomAccess)
        _difference_(that)
    }
    final def +=(d: Long): Pointer[A] = {
        Assert("must be RandomAccess", traversal <:< Traversal.RandomAccess)
        _offset(d); this
    }
    final def -=(d: Long): Pointer[A] = this += (-d)
    final def + (d: Long): Pointer[A] = copy += d
    final def - (d: Long): Pointer[A] = copy -= d
    final def < (that: Pointer[A]): Boolean = this - that < 0
    final def > (that: Pointer[A]): Boolean = this - that > 0
    final def <= (that: Pointer[A]): Boolean = this - that <= 0
    final def >= (that: Pointer[A]): Boolean = this - that >= 0

    protected def _offsetRead(d: Long): A = (this + d).read
    protected def _offsetWrite(d: Long, e: A): Unit = (this + d).write(e)
    final def apply(d: Long): A = _offsetRead(d)
    final def update(d: Long, e: A): Unit = _offsetWrite(d, e)

// debug
    protected def _invariant = { }

// utilities
    final def models(t: Traversal) = traversal <:< t
    final def advance(d: Long) = /.ptr_advance(d)./
    final def output: A => Pointer[A] = detail.PointerOutput(this, _)
    final def swap(that: Pointer[A]) = detail.PointerSwap(this, that)
    final def <=<(that: Pointer[A]) = new detail.PointerRng(this, that).rng
    final def copyIn(t: Traversal): Pointer[A] = if (traversal <:< t) copy else this
    final def pointer = this
    final def toExpr = Expr(this)
    final def / = toExpr
}


// prefix operations

object PointerPreOps extends PointerPreOps; trait PointerPreOps extends Namespace
        with PointerPre_*
        with PointerPre_++
        with PointerPre_--

object PointerPre_* extends PointerPre_*; trait PointerPre_* {
    object * {
        def apply[A](p: Pointer[A]): A = p.read
        def update[A](p: Pointer[A], e: A): Unit = p.write(e)

        def apply[A](p_d: (Pointer[A], Long)): A = p_d._1(p_d._2)
        def update[A](p_d: (Pointer[A], Long), e: A): Unit = p_d._1(p_d._2) = e
    }
}

object PointerPre_++ extends PointerPre_++; trait PointerPre_++ {
    final def ++[A](p: Pointer[A]): Pointer[A] = p.pre_++
}

object PointerPre_-- extends PointerPre_--; trait PointerPre_-- {
    final def --[A](p: Pointer[A]): Pointer[A] = p.pre_--
}


// errors

class NotReadablePointerError[A](val pointer: Pointer[A]) extends Error
class NotWritablePointerError[A](val pointer: Pointer[A]) extends Error
