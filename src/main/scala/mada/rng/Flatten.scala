
package mada.rng


import Implies._
import IsEmpty._


object Flatten extends Flatten; trait Flatten extends Predefs {
    class MadaRngFlatten[A](_1: Expr[Rng[Rng[A]]]) {
        def rng_flatten = FlattenExpr(_1, None).expr
        def rng_flatten(_2: Traversal) = FlattenExpr(_1, Some(_2)).expr
    }
    implicit def toMadaRngFlatten[A](_1: Expr[Rng[Rng[A]]]): MadaRngFlatten[A] = new MadaRngFlatten[A](_1)
}


case class FlattenExpr[A](_1: Expr[Rng[Rng[A]]], _2: Option[Traversal]) extends Expr[Rng[A]] {
    override def _eval = FlattenImpl(_1.eval, _2)
}


object FlattenImpl {
    def apply[A](r: Rng[Rng[A]], ot: Option[Traversal]): Rng[A] = {
        val (p, q) = r.toPair
        val t = ot.getOrElse(SinglePassTraversal)
        Assert("Flatten can't be RandomAccess", t >:> BidirectionalTraversal)
        Assert("requires " + t.toString, p.traversal <:< t)
        new FlattenPointer(p, q, t) <=< new FlattenPointer(q.copyIn(BidirectionalTraversal), q, t)
    }
}


class FlattenPointer[A](override val _base: Pointer[Rng[A]], val end: Pointer[Rng[A]], override val _traversal: Traversal)
        extends PointerAdapter[Rng[A], A, FlattenPointer[A]] {
    private var local: Pointer[A] = null
    resetLocalForward

    override def _read = {
        *(local)
    }

    override def _write(e: A) = {
        *(local) = e
    }

    override def _equals(that: FlattenPointer[A]) = {
        (base == that.base) && (base != end implies local == that.local)
    }

    override def _increment = {
        local.pre_++
        if (local == localRng.end) {
            base.pre_++
            resetLocalForward
        }
    }

    override def _copy = {
        val that = new FlattenPointer(base.copy, end, traversal)
        that.local = if (local eq null) null else local.copy
        that
    }

    override def _decrement = {
        if (base == end || local == localRng.begin) {
            base.pre_--
            resetLocalBackward
        }
        local.pre_--
    }

    override def toString = new StringBuilder().append("FlattenPointer of ").append(base).toString

    private def localRng = {
        *(base)
    }

    private def resetLocalForward = {
        while (base != end && localRng.toExpr.rng_isEmpty.eval) {
            base.pre_++
        }
        if (base != end) {
            local = localRng.begin
        }
    }

    private def resetLocalBackward = {
        while (localRng.toExpr.rng_isEmpty.eval) {
            base.pre_--
        }
        local = localRng.end
    }
}
