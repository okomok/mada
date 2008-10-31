
package mada.range


case class EqualsIf[E1_, E2](private val r2: Range[E2], private val f: (E1_, E2) => Boolean) extends RangeFunction[Boolean] {
    def apply[E1 <: E1_](r1: Range[E1]): Boolean = {
        r1.traversal min r2.traversal match {
            case RandomAccessTraversal() => ofRandomAccess(r1)
            case _ => ofSinglePass(r1)
        }
    }

    override def fromRange[E1] = EqualsIf[E1, E2](r2, f.asInstanceOf[(E1, E2) => Boolean]).apply(_)

    private def ofRandomAccess[E1 <: E1_](r1: Range[E1]): Boolean = {
        if (r1->Size != r2->Size)
            false
        else
            r1->EqualIf(r2.begin, f)
    }

    private def ofSinglePass[E1 <: E1_](r1: Range[E1]): Boolean = {
        val p1 = r1.begin; val q1 = r1.end
        val p2 = r2.begin; val q2 = r2.end
        while (p1 != q1 && p2 != q2) {
            if (!f(p1.read, p2.read))
                return false
            p1++/; p2++/
        }
        (p2 == q2) && (p1 == q1)
    }
}

case class Equals[E2](private val r2: Range[E2]) extends EqualsIf[E2, E2](r2, _ == _)
