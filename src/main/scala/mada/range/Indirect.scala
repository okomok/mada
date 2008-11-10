
package mada.range


object Indirect {
    trait MadaRangeIndirect[A] {
        def indirect(b: Range[Pointer[A]]): Range[A]
    }
    def toMadaRangeIndirect[A](b: Range[Pointer[A]]) = b match {
        case OutdirectRange(bb) => new MadaRangeIndirect[A] {
            def indirect(b: Range[Pointer[A]]) = bb
        }
        case _ => new MadaRangeIndirect[A] {
            def indirect(b: Range[Pointer[A]]) = new IndirectRange(b)
        }
    }
}

class IndirectRange[A](base: Range[Pointer[A]]) extends Range[A] {
    override val _begin = new IndirectPointer(base.begin)
    override val _end = new IndirectPointer(base.end)

    override def outdirect = base
}

class IndirectPointer[A](override val _base: Pointer[Pointer[A]])
        extends PointerAdapter[Pointer[A], A, IndirectPointer[A]] {
    override def _read = *(*(base))
    override def _write(e: A) { *(*(base)) = e }
    override def _clone = new IndirectPointer[A](base.clone)
}
