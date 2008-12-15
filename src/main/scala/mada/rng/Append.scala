

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Append extends Append; trait Append extends Predefs {
    class MadaRngAppend[A](_1: Expr.Of[Rng[A]]) {
        def append(_2: Expr.Of[Rng[A]]) = AppendExpr(_1, _2).expr
        def ++(_2: Expr.Of[Rng[A]]) = AppendExpr(_1, _2).expr
    }
    implicit def toMadaRngAppend[A](_1: Expr.Of[Rng[A]]): MadaRngAppend[A] = new MadaRngAppend[A](_1)
}


case class AppendExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[A]]) extends Expr.Transform[Rng[A]] {
    override protected def _default = AppendImpl(_1.eval, _2.eval)
}


object AppendImpl {
    def apply[A](rL: Rng[A], rR: Rng[A]): Rng[A] = {
        val (pL, qL) = rL.toPair
        val (pR, qR) = rR.toPair
        new AppendPointer(pL, qL, pR, pR.copyIn(Traversal.Forward)) <=<
            new AppendPointer(qL.copyIn(Traversal.Forward), qL, pR, qR)
    }
}


class AppendPointer[A](
    override protected val _base: Pointer[A],
    private val leftEnd: Pointer[A], private val rightBegin: Pointer[A],
    private var rightBase: Pointer[A])
        extends PointerAdapter[A, A, AppendPointer[A]] {
    override protected def _read = if (inLeft) *(base) else *(rightBase)

    override protected def _write(e: A) = if (inLeft) { *(base) = e } else { *(rightBase) = e }

    override protected def _traversal = leftEnd.traversal upper rightBegin.traversal

    override protected def _equals(that: AppendPointer[A]) = (base == that.base) && (rightBase == that.rightBase)

    override protected def _increment = {
        if (inLeft) {
            base.pre_++
        } else {
            rightBase.pre_++
        }
    }

    override protected def _copy = new AppendPointer(base.copy, leftEnd, rightBegin, rightBase.copy)

    override protected def _decrement = {
        if (!inLeft) {
            if (rightBase == rightBegin) {
                baseRef := leftEnd.copy.pre_--
            } else {
                rightBase.pre_--
            }
        } else {
            base.pre_--
        }
    }

    override protected def _offset(d: Long) = {
        if (d >= 0) {
            if (inLeft) {
                val over = d - (leftEnd - base)
                if (over < 0) {
                    base += d
                } else {
                    baseRef := leftEnd.copy
                    rightBase += over
                }
            } else {
                rightBase += d
            }
        } else {
            if (!inLeft) {
                val over = d - (rightBegin - rightBase)
                if (over >= 0) {
                    rightBase += d
                } else {
                    rightBase = rightBegin.copy
                    base += over
                }
            } else {
                base += d
            }
        }
    }

    override protected def _offsetRead(d: Long) = {
        if (d >= 0) {
            if (inLeft) {
                val over = d - (leftEnd - base)
                if (over < 0) {
                    *(base, + d)
                } else {
                    *(rightBase, + over)
                }
            } else {
                *(rightBase, + d)
            }
        } else {
            if (!inLeft) {
                val over = d - (rightBegin - rightBase)
                if (over >= 0) {
                    *(rightBase, + d)
                } else {
                    *(base, + over)
                }
            } else {
                *(base, + d)
            }
        }
    }

    override protected def _offsetWrite(d: Long, e: A) = {
        if (d >= 0) {
            if (inLeft) {
                val over = d - (leftEnd - base)
                if (over < 0) {
                    *(base, + d) = e
                } else {
                    *(rightBase, + over) = e
                }
            } else {
                *(rightBase, + d) = e
            }
        } else {
            if (!inLeft) {
                val over = d - (rightBegin - rightBase)
                if (over >= 0) {
                    *(rightBase, + d) = e
                } else {
                    *(base, + over) = e
                }
            } else {
                *(base, + d) = e
            }
        }
    }

    override protected def _difference(that: AppendPointer[A]) = {
        if (inLeft && that.inLeft) {
            base - that.base
        } else if (!inLeft && !that.inLeft) {
            rightBase - that.rightBase
        } else if (inLeft) {
            (base - leftEnd) + (that.rightBegin - that.rightBase)
        } else {
            Assert(!inLeft)
            (rightBase - rightBegin) + (that.leftEnd - that.base)
        }
    }

    private def inLeft = base != leftEnd
}
