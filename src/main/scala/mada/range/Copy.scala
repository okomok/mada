
package mada.range


object Copy {
    def apply[A](r1: Range[A], p2: Pointer[A]): Pointer[A] = { r1.forEach(p2.output); p2 }
}
