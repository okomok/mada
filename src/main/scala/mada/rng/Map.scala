
package mada.rng


object Map extends Map

trait Map extends Predefs {
    class MadaRngMap[From](_1: Expr[Rng[From]]) {
        def map[To](_2: Expr[From => To]) = MapExpr(_1, _2).expr
    }
    implicit def toMadaRngMap[From](_1: Expr[Rng[From]]) = new MadaRngMap(_1)
}


case class MapExpr[From, To](_1: Expr[Rng[From]], _2: Expr[From => To]) extends Expr[Rng[To]] {
    def eval = _1 match {
        case MapExpr(x1, x2) => MapImpl(x1.eval, _2.eval compose x2.eval) // map-map fusion
        case _ => MapImpl(_1.eval, _2.eval)
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
    override def _write(e: To) { throw new ErrorNotWritable(this) }
    override def _clone = new MapPointer(base.clone, function)
}
