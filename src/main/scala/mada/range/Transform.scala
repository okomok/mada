
package mada.range


object Transform {
    def apply[Z, A](base: Range[Z], function: Z => A): Range[A] = new TransformRange(base, function)
}

class TransformRange[Z, A](val base: Range[Z], val function: Z => A)
        extends PointerRange[A](new TransformPointer(base.begin, function), new TransformPointer(base.end, function)) {
    override def joint(that: Range[A]) = that match {
        case that: TransformRange[Z, A] if that.function == function => base.joint(that.base).transform(function)
        case _ => super.joint(that)
    }

    override def transform[B](f: A => B) = base.transform(f compose function)
}

class TransformPointer[Z, A](private val p: Pointer[Z], val function: Z => A)
        extends PointerAdapter[Z, A, TransformPointer[Z, A]](p) {
    override def _read = function(base.read)
    override def _write(e: A) = { throw ErrorNotWritable(this) }
    override def _clone = new TransformPointer(p.clone, function)
}
