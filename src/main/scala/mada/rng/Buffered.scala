
package mada.rng


import scala.collection.mutable.HashMap


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
        val m = new HashMap[Long, A]
        new BufferedPointer(r.begin, m) <=< new BufferedPointer(r.end, m)
    }
}

class BufferedPointer[A](val base: Pointer[A], map: HashMap[Long, A])
        extends PointerFacade[A, BufferedPointer[A]] {
    private var baseId = 0L

    override protected def _read = {
        val v = map.get(baseId)
        if (v.isEmpty) {
            val e = *(base)
            map.update(baseId, e)
            e
        } else {
            v.get
        }
    }

    override protected def _write(e: A) = { throw new NotWritablePointerError(this) }

    override protected def _traversal = ForwardTraversal

    override protected def _equals(that: BufferedPointer[A]) = {
        val thisInBuffer = map.contains(baseId)
        val thatInBuffer = map.contains(that.baseId)

        if (thisInBuffer && thatInBuffer) {
            baseId == that.baseId
        } else if (!thisInBuffer && !thatInBuffer) {
            base == that.base
        } else {
            false
        }
    }

    override protected def _increment = {
        val v = map.get(baseId)
        if (v.isEmpty) {
            map.update(baseId, *(base))
            try {
                base.pre_++
            } catch {
                case e => { map -= baseId; throw e }
            }
        }

        baseId += 1L
    }

    override protected def _copy = {
        val that = new BufferedPointer(base, map)
        that.baseId = baseId
        that
    }

    override def hashCode = long2Long(baseId).hashCode
    override def toString = new StringBuilder().append("BufferedPointer of ").append(base).toString
}
