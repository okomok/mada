
package mada.rng


object Map extends Map; trait Map extends Predefs {
    class MadaRngMap[From](_1: Expr.Of[Rng[From]]) {
        def map[To](_2: From => To) = MapExpr(_1, _2).expr
    }
    implicit def toMadaRngMap[From](_1: Expr.Of[Rng[From]]): MadaRngMap[From] = new MadaRngMap[From](_1)
}


case class MapExpr[From, To](override val _1: Expr.Of[Rng[From]], _2: From => To) extends Expr.Method[Rng[From], Rng[To]] {
    override def _default = _1 match {
        case MapExpr(x1, x2) => MapExpr(x1, _2 compose x2).eval // map-map fusion
        case _ => MapImpl(_1.eval, _2)
    }
}


object MapImpl {
    def apply[From, To](r: Rng[From], f: From => To): Rng[To] = {
        new MapPointer(r.begin, f) <=< new MapPointer(r.end, f)
    }
}

class MapPointer[From, To](override val _base: Pointer[From], val function: From => To)
        extends PointerAdapter[From, To, MapPointer[From, To]] {
    override def _read = function(*(base))
    override def _write(e: To) = { throw new NotWritablePointerError(this) }
    override def _copy = new MapPointer(base.copy, function)
    override def toString = new StringBuilder().append("MapPointer of ").append(base).toString
}
