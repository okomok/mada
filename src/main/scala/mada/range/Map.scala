
package mada.range


class MapPointer[From, To](private val p: Pointer[From], val function: From => To)
    extends PointerAdapter[From, To, MapPointer[From, To]](p) {
    override def _read = function(base.read)
    override def _write(e: To) = { throw NotWritable(this) }
    override def _clone = new MapPointer(p.clone, function)
}


class MonoMap[From, To](f: From => To) extends (Range[From] => Range[To]) {
    override final def apply(r: Range[From]) = new Range[To] {
        override def _begin = new MapPointer(r.begin, f)
        override def _end = new MapPointer(r.end, f)
    }
}

case class Map[From, To](f: From => To) extends RangeFunction[Range[To]] {
    override def fromRange[X] = new MonoMap[X, To](f.asInstanceOf[X => To])
}


object mytest {
    def apply(r: Range[Int], f: Int => Boolean, g: Boolean => Int) {
        val v: Long = r >> Map(f) >> Map(g) >> Filter(f) >> Size;
    }
}
