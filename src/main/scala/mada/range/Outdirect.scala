
package mada.range


object Outdirect {
    def apply[A](base: Range[A]): Range[Pointer[A]] = new OutdirectRange(base)
}

class OutdirectRange[A](val base: Range[A]) extends Range[Pointer[A]] {
    override val _begin = new OutdirectPointer(base.begin)
    override val _end = new OutdirectPointer(base.end)

    override def indirect[B] = if (base.isInstanceOf[Range[B]]) base.asInstanceOf[Range[B]] else super.indirect[B]
}

class OutdirectPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override def _read = base
    override def _write(e: Pointer[A]) = { throw new ErrorNotWritable(this) }
    override def _clone = new OutdirectPointer(base.clone)
}
