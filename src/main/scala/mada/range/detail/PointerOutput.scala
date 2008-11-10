
package mada.range.detail


class PointerOutput[A](val pointer: Pointer[A]) extends (A => Pointer[A]) {
    override def apply(e: A) = {
        *(pointer) = e
        ++(pointer)
    }
}
