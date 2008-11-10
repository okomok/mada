
package mada.range


object Indirect {
    def apply[A](r: Range[Pointer[A]]): Range[A] = new IndirectRange(r)

    object Operator {
        trait MadaRangeIndirectLeft[A] {
            def indirect: Range[A]
        }
        implicit def toMadaRangeIndirectLeft[A](l: Range[Pointer[A]]) = l match {
            case OutdirectRange(base) => new MadaRangeIndirectLeft[A] { override def indirect = base }
            case _ => new MadaRangeIndirectLeft[A] { override def indirect = apply(l) }
        }
    }
}

case class IndirectRange[A](base: Range[Pointer[A]]) extends Range[A] {
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
