
package mada.rng


object Map extends Map

trait Map {
    class MadaRngMap[From](_1: Expr[Rng[From]]) {
        def map[To](_2: Expr[From => To]) = MapExpr(_1, _2).expr
    }
    implicit def toMadaRngMap[From](_1: Expr[Rng[From]]) = new MadaRngMap(_1)
}


case class MapExpr[From, To](_1: Expr[Rng[From]], _2: Expr[From => To]) extends Expr[Rng[To]] {
    def eval = _1 match {
        case MapExpr(a1, a2) => new MapRng(a1.eval, _2.eval compose a2.eval) // map-fusion
        case _ => new MapRng(_1.eval, _2.eval)
    }
}


class MapRng[From, To](val base: Rng[From], val function: From => To) extends Rng[To] {
    override val _begin = new MapPointer(base.begin, function)
    override val _end = new MapPointer(base.end, function)
}

class MapPointer[From, To](override val _base: Pointer[From], val function: From => To)
        extends PointerAdapter[From, To, MapPointer[From, To]] {
    override def _read = function(*(base))
    override def _write(e: To) { throw new ErrorNotWritable(this) }
    override def _clone = new MapPointer(base.clone, function)
}
