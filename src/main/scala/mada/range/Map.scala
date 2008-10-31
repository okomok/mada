
package mada.range


case class Map[From, To](f: From => To) extends RangeFunction[Range[To]] {
    def apply[X <: From](r: Range[X]): Range[To] =
        new MapPointer[X, To](r.begin, f) <=< new MapPointer[X, To](r.end, f)
    override def fromRange[X] = Map(f.asInstanceOf[X => To]).apply[X](_)
}

class MapPointer[From, To](private val p: Pointer[From], val function: From => To)
    extends PointerAdapter[From, To, MapPointer[From, To]](p) {
    override def _read = function(base.read)
    override def _write(e: To) = { throw NotWritable(this) }
    override def _clone = new MapPointer(p.clone, function)
}


object mytest {
    def apply(r: Range[Int], f: Int => Boolean, g: Boolean => Int) {
        val v: Long = r->Map(f)->Map(g)->Filter(f)->AsRangeOf[Boolean]->Outdirect->Indirect.to[Boolean]->Size;
    }
}
