
package mada.range


object Indirect extends Indirect

trait Indirect {
    trait MadaRangeIndirect[A] extends IndirectImpl.Operator[A]
    implicit def toMadaRangeIndirect[A](b: Range[Pointer[A]]) = IndirectImpl(b)
}

object IndirectImpl {
    trait Operator[A] {
        def indirect: Range[A]
    }

    def apply[A](b: Range[Pointer[A]]) = b match {
        case OutdirectRange(bb) => new Operator[A] {
            def indirect = bb
        }
        case _ => new Operator[A] {
            def indirect = new IndirectRange(b)
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
