
package mada.range


object Indirect {
    def apply[To](base: Range[Pointer[To]]): Range[To] = new IndirectRange(base)
}

class UnsafeIndirect[To] {
    def apply[From](base: Range[From]): Range[To] = Indirect(base.asRangeOf[Pointer[To]])
}

class IndirectRange[To](base: Range[Pointer[To]]) extends Range[To] {
    override val _begin = new IndirectPointer(base.begin)
    override val _end = new IndirectPointer(base.end)

    override def outdirect = base
}

class IndirectPointer[To](override val _base: Pointer[Pointer[To]])
        extends PointerAdapter[Pointer[To], To, IndirectPointer[To]] {
    override def _read = *(*(base))
    override def _write(e: To) = *(*(base)) = e
    override def _clone = new IndirectPointer[To](base.clone)
}
