
package mada.range


object ReadOnly {
    def apply[A](base: Range[A]): Range[A] = new ReadOnlyRange(base)
}

class ReadOnlyRange[A](val base: Range[A])
        extends PointerRange[A](new ReadOnlyPointer(base.begin), new ReadOnlyPointer(base.end)) {
    override def readOnly = this
}

class ReadOnlyPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReadOnlyPointer[A]] {
    override def _write(e: A) = { throw ErrorNotWritable(this) }
    override def _clone = new ReadOnlyPointer(base.clone)
}
