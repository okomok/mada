
package mada.range


object Indirect {
    def apply[X](r: Range[Pointer[X]]): Range[X] =
        new IndirectPointer[X](r.begin) <=< new IndirectPointer[X](r.end)
    def to[E] = new IndirectTo[E]
}

class IndirectTo[E] extends RangeFunction[Range[E]] {
    def fromRange[X] = {(r: Range[X]) => Indirect(r->AsRangeOf[Pointer[E]])}
}

class IndirectPointer[E](private var p: Pointer[Pointer[E]])
        extends PointerAdapter[Pointer[E], E, IndirectPointer[E]](p) {
    override def _read = p.read.read
    override def _write(e: E) = p.read.write(e)
    override def _clone = new IndirectPointer[E](p.clone)
}
