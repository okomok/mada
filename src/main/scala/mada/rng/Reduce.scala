

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import FoldLeft._
import Pointer._


// reduceLeft

object ReduceLeft extends ReduceLeft; trait ReduceLeft extends Predefs {
    class MadaRngReduceLeft[A](_1: Expr.Of[Rng[A]]) {
        def reduceLeft[B >: A](_2: (B, A) => B) = ReduceLeftExpr(_1, _2).expr
    }
    implicit def toMadaRngReduceLeft[A](_1: Expr.Of[Rng[A]]): MadaRngReduceLeft[A] = new MadaRngReduceLeft[A](_1)
}

case class ReduceLeftExpr[A, B >: A](override val _1: Expr.Of[Rng[A]], _2: (B, A) => B) extends Expr.Method[Rng[A], B] {
    override protected def _default = ReduceLeftImpl(_1.eval, _2)
}

object ReduceLeftImpl {
    def apply[A, B >: A](r: Rng[A], f: (B, A) => B): B = {
        AssertNotEmpty(r)
        val (p, q) = r.toPair
        val z = *(p)
        (++(p) <=< q)./.foldLeft(z, f)./
    }
}


// reduceRight

object ReduceRight extends ReduceRight; trait ReduceRight extends Predefs {
    class MadaRngReduceRight[A](_1: Expr.Of[Rng[A]]) {
        def reduceRight[B >: A](_2: (A, B) => B) = ReduceRightExpr(_1, _2).expr
    }
    implicit def toMadaRngReduceRight[A](_1: Expr.Of[Rng[A]]): MadaRngReduceRight[A] = new MadaRngReduceRight[A](_1)
}

case class ReduceRightExpr[A, B >: A](_1: Expr.Of[Rng[A]], _2: (A, B) => B) extends Expr.Alias[Rng[A], B] {
    override protected def _alias = ReduceLeftExpr(ReverseExpr(_1), { (b: B, a: A) => _2(a, b) })
}
