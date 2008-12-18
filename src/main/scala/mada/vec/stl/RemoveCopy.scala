

// RemoveCopyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object RemoveCopy extends RemoveCopy; trait RemoveCopy extends Predefs {
    class MadaVecStlRemoveCopy[A](_1: Expr.Of[Vector[A]]) {
        def stl_removeCopy[F <: (A => Any)](_2: F, _3: A) = RemoveCopyExpr(_1, _2, _3).expr
        def stl_removeCopyIf[F <: (A => Any)](_2: F, _3: A => Boolean) = RemoveCopyIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecStlRemoveCopy[From](_1: Expr.Of[Vector[From]]): MadaVecStlRemoveCopy[From] = new MadaVecStlRemoveCopy[From](_1)
}


case class RemoveCopyExpr[A, F <: (A => Any)](_1: Expr.Of[Vector[A]], _2: F, _3: A) extends Expr.Alias[Vector[A], F] {
    override protected def _alias = RemoveCopyIfExpr(_1, _2, { (e: A) => e == _3 })
}

case class RemoveCopyIfExpr[A, F <: (A => Any)](override val _1: Expr.Of[Vector[A]], _2: F, _3: A => Boolean) extends Expr.Method[Vector[A], F] {
    override protected def _default = {
        CopyIfExpr(_1, _2, { (e: A) => !_3(e) }).eval
        _2
    }
}
