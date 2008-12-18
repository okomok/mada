

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Copy extends Copy; trait Copy extends Predefs {
    class MadaVecStlCopy[A](_1: Expr.Of[Vector[A]]) {
        def stl_copy(_2: A => Any) = CopyExpr(_1, _2).expr
        def stl_copyIf(_2: A => Any, _3: A => Boolean) = CopyIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecStlCopy[A](_1: Expr.Of[Vector[A]]): MadaVecStlCopy[A] = new MadaVecStlCopy[A](_1)
}


case class CopyExpr[A](_1: Expr.Of[Vector[A]], _2: A => Any) extends Expr.Alias[Vector[A], Unit] {
    override protected def _alias = ForEachExpr(_1, _2)
}

case class CopyIfExpr[A](_1: Expr.Of[Vector[A]], _2: A => Any, _3: A => Boolean) extends Expr.Alias[Vector[A], Unit] {
    override protected def _alias = ForEachExpr(_1, { (e: A) => if (_3(e)) _2(e) })
}



/*
case class CopyIfExpr[A, A >: A](_1: Expr.Of[Vector[A]], _2: Vector.Pointer[A], _3: A => Boolean)
extends Expr.Method[Vector[A], Vector.Pointer[A]] {
    override protected def _default = {
        val (v, i, j) = _1.eval.toTriple
        CopyIfFunc(v, i, j, _2, _3)
    }
}


object CopyIfFunc {
    def apply[A, A >: A](* : Vector[A], first: Long, __last: Long, __result: Vector.Pointer[A], __pred: A => Boolean): Vector.Pointer[A] = {
        var __first = first

        var __n = __last - __first
        while (__n > 0) {
            if (__pred(*(__first))) {
                *(__result) = *(__first)
                __result += 1
            }
            __first += 1
            __n -= 1
        }
        return __result
    }
}*/
