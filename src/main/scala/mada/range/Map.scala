
package mada.range


object Map {
    def apply[Z, A](base: Range[Z], function: Z => A): Range[A] = new MapRange(base, function)
}

class MapRange[Z, A](val base: Range[Z], val function: Z => A)
        extends PointerRange[A](new MapPointer(base.begin, function), new MapPointer(base.end, function)) {
    override def concat(that: Range[A]) = that match {
        case that: MapRange[Z, A] if that.function == function => base.concat(that.base).map(function) // BUBUG: A and Z is unchecked
        case _ => super.concat(that)
    }

    override def map[B](f: A => B) = base.map(f compose function)
}

class MapPointer[Z, A](override val _base: Pointer[Z], val function: Z => A)
        extends PointerAdapter[Z, A, MapPointer[Z, A]] {
    override def _read = function(*(base))
    override def _write(e: A) = { throw new ErrorNotWritable(this) }
    override def _clone = new MapPointer(base.clone, function)
}
