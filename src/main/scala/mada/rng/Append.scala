
package mada.rng


import Pointer._


object Append extends Append; trait Append extends Predefs {
    class MadaRngAppend[A](_1: Expr[Rng[A]]) {
        def rng_append(_2: Expr[Rng[A]]) = AppendExpr(_1, _2).expr
        def rng_++(_2: Expr[Rng[A]]) = AppendExpr(_1, _2).expr
    }
    implicit def toMadaRngAppend[A](_1: Expr[Rng[A]]): MadaRngAppend[A] = new MadaRngAppend[A](_1)
}


case class AppendExpr[A](_1: Expr[Rng[A]], _2: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = AppendImpl(_1.eval, _2.eval)
}


object AppendImpl {
    def apply[A](rL: Rng[A], rR: Rng[A]): Rng[A] = {
        val (pL, qL) = rL.toPair
        val (pR, qR) = rR.toPair
        new AppendPointer(pL, qL, pR, pR.copyIn(ForwardTraversal)) <=<
            new AppendPointer(qL.copyIn(ForwardTraversal), qL, pR, qR)
    }
}


class AppendPointer[A](
    override val _base: Pointer[A],
    private val leftEnd: Pointer[A], private val rightBegin: Pointer[A],
    private var rightBase: Pointer[A])
        extends PointerAdapter[A, A, AppendPointer[A]] {
    override def _read = if (inLeft) *(base) else *(rightBase)
    override def _write(e: A) = if (inLeft) { *(base) = e } else { *(rightBase) = e }
    override def _traversal = leftEnd.traversal upper rightBegin.traversal
    override def _equals(that: AppendPointer[A]) = (base == that.base) && (rightBase == that.rightBase)
    override def _increment = AppendPointerIncrement(base, rightBase, leftEnd)
    override def _copy = new AppendPointer(base.copy, leftEnd, rightBegin, rightBase.copy)

    override def _decrement = {
        val (pL, qL) = (new ReversePointer(rightBase.copy), new ReversePointer(rightBegin.copy))
        val pR = new ReversePointer(base)
        AppendPointerIncrement(pL, pR, qL)
        baseRef := pR.base
        rightBase = pL.base
    }

    override def _offset(d: Long) = {
        if (d >= 0) {
            AppendPointerOffset(baseRef, rightBase, d, leftEnd)
        } else {
            val (pL, qL) = (new Ref(new ReversePointer(rightBase.copy).pointer), new ReversePointer(rightBegin.copy))
            val pR = new ReversePointer(base)
            AppendPointerOffset(pL, pR, -d, qL)
            baseRef := pR.base
            rightBase = pL.deref.asInstanceOf[ReversePointer[A]].base
        }
    }

    override def _difference(that: AppendPointer[A]) = {
        if (inLeft && that.inLeft) {
            base - that.base
        } else if (!inLeft && !that.inLeft) {
            rightBase - that.rightBase
        } else if (inLeft) {
            (base - leftEnd) + (that.rightBegin - that.rightBase)
        } else {
            Assert("impossible", !inLeft)
            (rightBase - rightBegin) + (that.leftEnd - that.base)
        }
    }

    private def inLeft = base != leftEnd
}


object AppendPointerIncrement {
    def apply[A](pL: Pointer[A], pR: Pointer[A], qL: Pointer[A]): Unit = {
        if (pL != qL) {
            ++(pL)
        } else {
            ++(pR)
        }
    }
}

object AppendPointerOffset {
    def apply[A](pL: Ref[Pointer[A]], pR: Pointer[A], d: Long, qL: Pointer[A]): Unit = {
        Assert("impossible", d >= 0)

        if (pL.deref != qL) {
            val dL = qL - pL.deref
            if (d > dL) {
                pL := qL
                pR += (d - dL) // paren is needed in scala 2.7.1
            }
            else {
                pL.deref += d
            }
        }
        else {
            pR += d
        }
    }
}
