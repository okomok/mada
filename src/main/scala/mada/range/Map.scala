
package mada.range


/*

object Map {
    def apply[E, R](f: E => R)(r: Range[E]): Range[E] = new Range[E] {
        override def _begin = new MapPointer(r.begin, f)
        override def _end = new MapPointer(r.end, f)
    }
}

*/

class MapPointer[From, To](private val p: Pointer[From], val function: From => To)
extends PointerAdapter[From, To, MapPointer[From, To]](p) {
    override def _read = function(base.read)
    override def _write(e: To) = { throw NotWritable(this) }
    override def _clone = new MapPointer(p.clone, function)
}
