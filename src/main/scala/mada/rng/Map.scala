
package mada.rng


object Map {
    def apply[Z, A](base: Rng[Z], function: Z => A): Rng[A] = new MapRng(base, function)
}

class MapRng[Z, A](val base: Rng[Z], val function: Z => A) extends Rng[A] {
    override val _begin = new MapPointer(base.begin, function)
    override val _end = new MapPointer(base.end, function)

    override def map[B](f: A => B) = base.map(f compose function)
}

class MapPointer[Z, A](override val _base: Pointer[Z], val function: Z => A)
        extends PointerAdapter[Z, A, MapPointer[Z, A]] {
    override def _read = function(*(base))
    override def _write(e: A) { throw new ErrorNotWritable(this) }
    override def _clone = new MapPointer(base.clone, function)
}
