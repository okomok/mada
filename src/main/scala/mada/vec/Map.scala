

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Map extends Map; trait Map extends Predefs {
    class MadaVecMap[From](_1: Expr.Of[Vector[From]]) {
        def map[To](_2: From => To) = MapExpr(_1, _2).expr
    }
    implicit def toMadaVecMap[From](_1: Expr.Of[Vector[From]]): MadaVecMap[From] = new MadaVecMap[From](_1)
}


case class MapExpr[From, To](override val _1: Expr.Of[Vector[From]], _2: From => To)
        extends Expr.Method[Vector[From], Vector[To]] {
    override protected def _default = _1 match {
        case MapExpr(x1, x2) => MapExpr(x1, _2 compose x2).eval // map-map fusion
        case _ => new MapVector(_1.eval, _2)
    }
}


class MapVector[From, To](override val * : Vector[From], f: From => To)
        extends Vector.Adapter[From, To] with Vector.NotWritable[To] {
    override def apply(i: Long) = f(*(i))
}
