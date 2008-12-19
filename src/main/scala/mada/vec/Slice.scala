

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Slice extends Slice; trait Slice extends Predefs {
    class MadaVecSlice[A](_1: Expr.Of[Vector[A]]) {
        def slice(_2: Long, _3: Long) = SliceImpl(_1, _2, _3)
    }
    implicit def toMadaVecSlice[A](_1: Expr.Of[Vector[A]]): MadaVecSlice[A] = new MadaVecSlice[A](_1)
}


object SliceImpl {
    import Drop._
    import Take._

    def apply[A](_1: Expr.Of[Vector[A]], _2: Long, _3: Long) = _1.drop(_2).take(_3 - _2)
}
