

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Loop extends Loop; trait Loop extends Predefs {
    class MadaVecLoop[A](_1: Expr.Of[Vector[A]]) {
        def loop(_2: A => Boolean) = LoopExpr(_1, _2).expr
    }
    implicit def toMadaVecLoop[A](_1: Expr.Of[Vector[A]]): MadaVecLoop[A] = new MadaVecLoop[A](_1)
}


case class LoopExpr[A](override val _1: Expr.Of[Vector[A]], _2: A => Boolean) extends Expr.Method[Vector[A], Unit] {
    override protected def _default = _1 match {
        case MapExpr(x1, x2) => LoopExpr(x1, _2 compose x2).eval // loop-map fusion
        case _ => LoopImpl(_1.eval, _2)
    }
}


object LoopImpl {
    def apply[A](* : Vector[A], __f: A => Boolean) {
        var (__first, __last) = *.toRange

        while (__first != __last && __f(*(__first))) {
            __first += 1
        }
    }
}
