

// right Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Copyright (c) 1996,1997
 * Silicon Graphics Computer Systems, Inc.
 *
 * Copyright (c) 1997
 * Moscow Center for SPARC Technology
 *
 * Copyright (c) 1999
 * Boris Fomitchev
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 * Permission to use or copy this software for any purpose is hereby granted
 * without fee, provided the above notices are retained on all copies.
 * Permission to modify the code and to distribute modified code is granted,
 * provided the above notices are retained, and a notice that the code was
 * modified is included with the above copyright notice.
 *
 */


package mada.vec.stl


object Remove extends Remove; trait Remove extends Predefs {
    class MadaVecStlRemove[A](_1: Expr.Of[Vector[A]]) {
        def stl_remove(_2: A) = RemoveExpr(_1, _2).expr
        def stl_removeIf(_2: A => Boolean) = RemoveIfExpr(_1, _2).expr
    }
    implicit def toMadaVecStlRemove[From](_1: Expr.Of[Vector[From]]): MadaVecStlRemove[From] = new MadaVecStlRemove[From](_1)
}


case class RemoveExpr[A](_1: Expr.Of[Vector[A]], _2: A) extends Expr.Alias[Vector[A], Long] {
    override protected def _alias = RemoveIfExpr(_1, { (e: A) => e == _2 })
}

case class RemoveIfExpr[A](override val _1: Expr.Of[Vector[A]], _2: A => Boolean) extends Expr.Method[Vector[A], Long] {
    override protected def _default = {
        val (v, i, j) = _1.eval.toTriple
        RemoveIfFunc(v, i, j, _2)
    }
}


object RemoveIfFunc {
    import Find._
    import RemoveCopy._
    import Window._

    def apply[A](* : Vector[A], first: Long, __last: Long, __pred: A => Boolean): Long = {
        var __first = first

        __first = *./.stl_findIf(__pred)./
        if ( __first == __last ) {
            __first
        } else {
            var __next = __first
            __next += 1
            val outFirst = *.out(__first)
            *./.window(__next, __last).stl_removeCopyIf(outFirst, __pred)./
            outFirst.index
        }
    }
}
