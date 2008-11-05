
package mada.range



object Joint {
    def apply[A](r1: Range[A], r2: Range[A]): Range[A] = r1 // new JointRange(r1, r2)
}

/*
class JointRange[A](r1: Range[A], r2: Range[A])
        extends {
            val p1 = r1.begin
            val q1 = r1.end
            val p2 = r2.begin
            val q2 = r2.end
        } with PointerRange[A](
            new ConcatPointer[A](p1, , r1.end) {
}


class ConcatPointer[A](
        private val pL~: Pointer[A], private val last1~: Pointer[A],
        private val first2~: Pointer[A], private val p2~: Pointer[A]) {
            extends PointerFacade[A, ConcatPointer[A]] {
    val pL = Ref(pL~)
    val lastL = lastL~.immutable.readonly
    val firstR = firstR~.immutable.readonly
    val pR = pR~
    override def _read = if (inLeft) p1.read else p2.read
    override def _traversal = p1.traversal min p2.traversal
    override def _equals(that: ConcatPointer[A]) = (p1 == that.p1) && (p2 == that.p2)
    override def _increment = ConcatPointerIncrement(p1,
    override def _clone = new NumberPointer(n)
    override def _hashCode = n.toInt // BUGBUG
    override def _decrement = n = n - 1
    override def _offset(d: Long) = n += d
    override def _difference(that: NumberPointer) = n - that.n

    private def inLeft = p1 != q1
}


object ConcatPointerIncrement {
    def apply[A](p1: Pointer[A], p2: Pointer[A], last1: Pointer[A]) {
        if (p1 != last1)
            ++(p1)
        else
            ++(p2)
    }
}

object ConcatPointerOffset {
    def apply[A](pL: Ref[Pointer[A]], pR: Pointer[A], d: Long, lastL: Pointer[A]): (Pointer[A], Pointer[A]) = {
        Assert("", d >= 0)

        if (pL.deref != lastL) {
            val dL = lastL - pL.deref;
            if (n > dL) {
                pL.deref = lastL;
                pR += n - dL;
            }
            else {
                pL.deref += n;
            }
        }
        else {
            pR += n;
        }
    }
}

*/
