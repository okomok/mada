
package mada.range


object Transform {
    def apply[Z, A](r: Range[Z], f: Z => A): Range[A] = new TransformRange(r, f);
//        new TransformPointer(r.begin, f) <=< new TransformPointer(r.end, f)
}

class TransformRange[Z, A](private val r: Range[Z], private val f: Z => A)
        extends PointerRange[A](new TransformPointer(r.begin, f), new TransformPointer(r.end, f)) {
    override def joint(that: Range[A]) = that match {
        case that: TransformRange[Z, A] if that.f == f => r.joint(that.r).transform(f)
        case _ => super.joint(that)
    }

    override def transform[B](g: A => B) = r.transform(g compose f)
}

class TransformPointer[Z, A](private val p: Pointer[Z], val function: Z => A)
        extends PointerAdapter[Z, A, TransformPointer[Z, A]](p) {
    override def _read = function(base.read)
    override def _write(e: A) = { throw NotWritable(this) }
    override def _clone = new TransformPointer(p.clone, function)
}
