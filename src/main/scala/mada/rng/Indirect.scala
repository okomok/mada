
package mada.rng


object Indirect extends Indirect

trait Indirect {
    trait MadaRngIndirect[A] extends IndirectImpl.Operator[A]
    implicit def toMadaRngIndirect[A](b: Rng[Pointer[A]]) = IndirectImpl(b)
}

object IndirectImpl {
    trait Operator[A] {
        def indirect: Rng[A]
    }

    def apply[A](b: Rng[Pointer[A]]) = b match {
        case OutdirectRng(bb) => new Operator[A] {
            def indirect = bb
        }
        case _ => new Operator[A] {
            def indirect = new IndirectRng(b)
        }
    }
}

class IndirectRng[A](base: Rng[Pointer[A]]) extends Rng[A] {
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
