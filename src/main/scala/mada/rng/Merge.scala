

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Merge extends Merge; trait Merge extends Predefs {
    class MadaRngMerge[A](_1: Expr.Of[Rng[A]], lt: (A, A) => Boolean) {
        def merge(_2: Rng[A]) = MergeExpr(_1, _2, new DefaultMergeRoutine(lt)).expr
    }
    class MadaRngMergeWith[A](_1: Expr.Of[Rng[A]]) {
        def mergeWith(_2: Rng[A], lt: (A, A) => Boolean) = MergeExpr(_1, _2, new DefaultMergeRoutine(lt)).expr
    }
    implicit def toMadaRngMerge[A <% Ordered[A]](_1: Expr.Of[Rng[A]]): MadaRngMerge[A] = new MadaRngMerge[A](_1, _ < _)
    implicit def toMadaRngMergeWith[A](_1: Expr.Of[Rng[A]]): MadaRngMergeWith[A] = new MadaRngMergeWith[A](_1)
}


case class MergeExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[A]], _3: MergeRoutine[A]) extends Expr.Transform[Rng[A]] {
    override protected def _default = MergeImpl(_1.eval, _2.eval, _3)
}


object MergeImpl {
    def apply[A](r1: Rng[A], r2: Rng[A], mr: MergeRoutine[A]): Rng[A] = {
        val (p1, q1) = r1.toPair
        val (p2, q2) = r2.toPair
        new MergePointer(p1, q1, p2, q2, mr) <=<
            new MergePointer(q1.copyIn(Traversal.Bidirectional), q1, q2.copyIn(Traversal.Bidirectional), q2, mr)
    }
}


trait MergeRoutine[A] {
    protected def lt: (A, A) => Boolean

    def beforeYield(r1: Rng[A], r2: Rng[A]): (Pointer[A], Pointer[A])
    def doYield(r1: Rng[A], r2: Rng[A]): A
    def afterYield(r1: Rng[A], r2: Rng[A]): (Pointer[A], Pointer[A])

    final def min(p1: Pointer[A], p2: Pointer[A]): A = {
        val (e1, e2) = (*(p1), *(p2))
        if (lt(e2, e1)) e2 else e1
    }
}


class MergePointer[A](
    override val _base: Pointer[A], end1: Pointer[A],
    private var base2: Pointer[A], end2: Pointer[A], routine: MergeRoutine[A])
        extends PointerAdapter[A, A, MergePointer[A]] {
    assign(routine.beforeYield(base <=< end1, base2 <=< end2))

    override protected def _read = routine.doYield(base <=< end1, base2 <=< end2)
    override protected def _traversal = base.traversal upper Forward upper base2.traversal
    override protected def _equals(that: MergePointer[A]) = base == that.base && base2 == that.base2
    override protected def _increment = {
        Assert("out of MergeRng", base != end1)
        Assert("out of MergeRng", base2 != end2)
        assign(routine.afterYield(base <=< end1, base2 <=< end2))
        assign(routine.beforeYield(base <=< end1, base2 <=< end2))
    }

    private def assign(p1p2: (Pointer[A], Pointer[A])): Unit = {
        baseRef := p1p2._1; base2 = p1p2._2
    }
}


class DefaultMergeRoutine[A](override val lt: (A, A) => Boolean) extends MergeRoutine[A] {
    override def beforeYield(r1: Rng[A], r2: Rng[A]): (Pointer[A], Pointer[A]) = {
        (r1.begin, r2.begin)
    }

    override def doYield(r1: Rng[A], r2: Rng[A]) = {
        val (p1, q1) = r1.toPair
        val (p2, q2) = r2.toPair

        if (p1 == q1) {
            *(p2)
        } else if (p2 == q2) {
            *(p1)
        } else {
            min(p1, p2)
        }
    }

    override def afterYield(r1: Rng[A], r2: Rng[A]) = {
        val (p1, q1) = r1.toPair
        val (p2, q2) = r2.toPair

        if (p1 == q1) {
            ++(p2)
        } else if (p2 == q2) {
            ++(p1)
        } else if (lt(*(p2), *(p1))) {
            ++(p2)
        } else {
            ++(p1)
        }
        (p1, p2)
    }
}
