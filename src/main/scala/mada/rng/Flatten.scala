
package mada.rng


import Implies._
import IsEmpty._


object Flatten extends Flatten; trait Flatten extends Predefs {
    class MadaRngFlatten[A](_1: Expr.Of[Rng[Rng[A]]]) {
        def flatten = FlattenExpr(_1, None).expr
        def flatten(_2: Traversal) = FlattenExpr(_1, Some(_2)).expr
    }
    implicit def toMadaRngFlatten[A](_1: Expr.Of[Rng[Rng[A]]]): MadaRngFlatten[A] = new MadaRngFlatten[A](_1)
}


case class FlattenExpr[A](override val _1: Expr.Of[Rng[Rng[A]]], _2: Option[Traversal]) extends Expr.Method[Rng[Rng[A]], Rng[A]] {
    override protected def _default = FlattenImpl(_1.eval, _2)
}


object FlattenImpl {
    def apply[A](r: Rng[Rng[A]], localTrv: Option[Traversal]): Rng[A] = {
        val (p, q) = r.toPair
        val st = p.traversal
        val lt = localTrv.getOrElse(Traversal.SinglePass)
        val t = st upper Traversal.Bidirectional upper lt // Oven was wrong.
        new FlattenPointer(p, q, t) <=< new FlattenPointer(q.copyIn(Traversal.Bidirectional), q, t)
    }
}


class FlattenPointer[A](override protected val _base: Pointer[Rng[A]], val end: Pointer[Rng[A]], override protected val _traversal: Traversal)
        extends PointerAdapter[Rng[A], A, FlattenPointer[A]] {
    private var local: Pointer[A] = null
    resetLocalForward

    override protected def _read = {
        *(local)
    }

    override protected def _write(e: A) = {
        *(local) = e
    }

    override protected def _equals(that: FlattenPointer[A]) = {
        (base == that.base) && (base != end implies local == that.local)
    }

    override protected def _increment = {
        local.pre_++
        if (local == localRng.end) {
            base.pre_++
            resetLocalForward
        }
    }

    override protected def _copy = {
        val that = new FlattenPointer(base.copy, end, traversal)
        that.local = if (local eq null) null else local.copy
        that
    }

    override protected def _decrement = {
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
        while (base != end && localRng./.isEmpty./) {
            base.pre_++
        }
        if (base != end) {
            local = localRng.begin
        }
    }

    private def resetLocalBackward = {
        while (localRng./.isEmpty./) {
            base.pre_--
        }
        local = localRng.end
    }
}
