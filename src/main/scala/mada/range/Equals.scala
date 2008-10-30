
package mada.range


class EqualTo[E1, E2] extends ((E1, E2) => Boolean) {
    final override def apply(e1: E1, e2: E2) = e1 == e2
}

class Equals[E1, E2] {
    final def apply(r1: Range[E1], r2: Range[E2]): Boolean = apply(r1, r2, new EqualTo[E1, E2])

    final def apply(r1: Range[E1], r2: Range[E2], f: (E1, E2) => Boolean): Boolean = {
        r1.traversalTag min r2.traversalTag match {
            case RandomAccessTraversalTag() => ofRandomAccess(r1, r2, f)
            case _ => ofSinglePass(r1, r2, f)
        }
    }

    private final def ofRandomAccess(r1: Range[E1], r2: Range[E2], f: (E1, E2) => Boolean): Boolean = {
        if (Size(r1) != Size(r2))
            false
        else
            ofSinglePass(r1, r2, f) // TODO
    }

    private final def ofSinglePass(r1: Range[E1], r2: Range[E2], f: (E1, E2) => Boolean): Boolean = {
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
