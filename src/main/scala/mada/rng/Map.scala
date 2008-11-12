
package mada.rng


object Map extends Map

trait Map {
    class MadaRngMap[From](base: Expr[Rng[From]]) {
        def map[To](function: From => To) = MapExpr(base, function)
    }
    implicit def toMadaRngMap[To](base: Expr[Rng[To]]) = new MadaRngMap(base)

    class MadaRngMap2[From, To](base: MapExpr[From, To]) {
        def map[Far](function: To => Far) = MapExpr(base.base, function compose base.function)
    }
    implicit def toMadaRngMap2[From, To](base: MapExpr[From, To]) = new MadaRngMap2(base)
}


case class MapExpr[From, To](base: Expr[Rng[From]], function: From => To) extends Expr[Rng[To]] {
    def eval = base match {
        case MapExpr(b, f) => new MapRng(b.eval, function compose f)
        case _ => new MapRng(base.eval, function)
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
