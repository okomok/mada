

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import scala.collection.jcl.HashMap


object Buffered extends Buffered; trait Buffered extends Predefs {
    class MadaRngBuffered[A](_1: Expr.Of[Rng[A]]) {
        def buffered = BufferedExpr(_1).expr
    }
    implicit def toMadaRngBuffered[A](_1: Expr.Of[Rng[A]]): MadaRngBuffered[A] = new MadaRngBuffered[A](_1)
}


case class BufferedExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Rng[A]] {
    override protected def _default = BufferedImpl(_1.eval)
}


object BufferedImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        new BufferedPointer(r.begin, new HashMap[Long, A]) <=< new BufferedPointer(r.end, null)
    }
}

class BufferedPointer[A](val base: Pointer[A], map: HashMap[Long, A])
        extends PointerFacade[A, BufferedPointer[A]] {
    private var baseId = 0L

    override protected def _read = buffering

    override protected def _traversal = Forward

    override protected def _equals(that: BufferedPointer[A]) = {
        val thisInBuffer = inBuffer
        val thatInBuffer = that.inBuffer

        if (thisInBuffer && thatInBuffer) {
            baseId == that.baseId
        } else if (!thisInBuffer && !thatInBuffer) {
            base == that.base
        } else {
            false
        }
    }

    override protected def _increment = {
        buffering
        baseId += 1L
    }

    override protected def _copy = {
        val that = new BufferedPointer(base, map)
        that.baseId = baseId
        that
    }

    override def hashCode = detail.LongHashCode(baseId)
    override def toString = new StringBuilder().append("BufferedPointer of ").append(base).toString

    private def buffering: A = {
        val v = map.get(baseId)
        if (v.isEmpty) {
            val e = *(base)
            map.put(baseId, e)
            try {
                base.pre_++
            } catch {
                case any => { map.removeKey(baseId); throw any }
            }
            e
        } else {
            v.get
        }
    }

    private def inBuffer: Boolean = {
        if (null eq map) {
            false
        } else {
            map.contains(baseId)
        }
    }
}
