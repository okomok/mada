
package mada.range


class EqualTo[E1_, E2_] extends ((E1_, E2_) => Boolean) {
    final override def apply(e1: E1_, e2: E2_) = e1 == e2
}

case class Equals[E1_, E2](r2: Range[E2], f: (E1_, E2) => Boolean) extends RangeFunction[Boolean] {
    def apply[E1 <: E1_](r1: Range[E1]): Boolean = {
        r1.traversal min r2.traversal match {
            case RandomAccessTraversal() => ofRandomAccess(r1)
            case _ => ofSinglePass(r1)
        }
    }

    override def fromRange[E1] = Equals[E1, E2](r2, f.asInstanceOf[(E1, E2) => Boolean]).apply(_)

    private def ofRandomAccess[E1 <: E1_](r1: Range[E1]): Boolean = {
        if (Size(r1) != Size(r2))
            false
        else
            r1->Equal(r2.begin, f)
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
