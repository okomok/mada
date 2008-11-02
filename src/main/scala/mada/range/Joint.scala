
package mada.range


object Joint {
    def apply[A](r1: Range[A], r2: Range[A]): Range[A] = new JointRange(r1, r2)
}


class JointRange[A](private val r1: Range[A], private val r2: Range[A])
        extends PointerRange[A](r1.begin, r1.end) {
}
