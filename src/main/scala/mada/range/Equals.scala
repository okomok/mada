
package mada.range


object Equals {
    def apply[E1, E2](r1: Range[E1], r2: Range[E2], f: (E1, E2) => Boolean): Boolean = {
        r1.traversal min r2.traversal match {
            case RandomAccessTraversal() => inRandomAccess(r1, r2, f)
            case SinglePassTraversal() => inSinglePass(r1, r2, f)
        }
    }

    def apply[E](r1: Range[E], r2: Range[E]): Boolean = apply[E, E](r1, r2, _ == _)

    private def inRandomAccess[E1, E2](r1: Range[E1], r2: Range[E2], f: (E1, E2) => Boolean): Boolean = {
        if (r1.size != r2.size)
            false
        else
            r1.equal(r2.begin, f)
    }

    private def inSinglePass[E1, E2](r1: Range[E1], r2: Range[E2], f: (E1, E2) => Boolean): Boolean = {
        val p1 = r1.begin; val q1 = r1.end
        val p2 = r2.begin; val q2 = r2.end
        while (p1 != q1 && p2 != q2) {
            if (!f(*(p1), *(p2)))
                return false
            ++(p1); ++(p2)
        }
        (p2 == q2) && (p1 == q1)
    }
}
