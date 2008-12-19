

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec

/*
object Remove extends Remove; trait Remove extends Predefs {
    class MadaVecRemove[A](_1: Expr.Of[Vector[A]]) {
        def remove(_2: A => Boolean) = RemoveExpr(_1, _2).expr
    }
    implicit def toMadaVecRemove[A](_1: Expr.Of[Vector[A]]): MadaVecRemove[A] = new MadaVecRemove[A](_1)
}


case class RemoveExpr[A](override val _1: Expr.Of[Vector[A]], _2: A => Boolean) extends Expr.Transform[Vector[A]] {
    override protected def _default = _1 match {
        case RemoveExpr(x1, x2) => RemoveExpr(x1, {(e: A) => x2(e) || _2(e)}).eval // remove-remove fusion
        case _ => RemoveImpl(_1.eval, _2)
    }
}


object RemoveImpl {
    import Force._
    import Window._
    import stl.Remove._

    def apply[A](* : Vector[A], pred: A => Boolean): Vector[A] = {
        // This seems better than removeCopyIf into ArrayList.
        val v = *./.force./
        v./.window(0, v./.stl_removeIf(pred)./)./
    }
}
*/
