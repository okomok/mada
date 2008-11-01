
package mada.range


case class EqualIf[E1_, E2](private val p2: Pointer[E2], private val f: (E1_, E2) => Boolean)
        extends RangeFunction[Boolean] {
    def apply[E1 <: E1_](r1: Range[E1]): Boolean = {
        val p1 = r1.begin; val q1 = r1.end
        while (p1 != q1) {
            if (!f(*(p1), *(p2)))
                return false
            p1++/; p2++/
        }
        true
    }

    override def fromRange[E1] = EqualIf[E1, E2](p2, f.asInstanceOf[(E1, E2) => Boolean]).apply(_)
}

case class Equal[E2](private val p2: Pointer[E2]) extends EqualIf[E2, E2](p2, _ == _)
