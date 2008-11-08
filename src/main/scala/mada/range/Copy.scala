
package mada.range


object Copy {
    def apply[A, B >: A](r1: Range[A], p2: Pointer[B]): Pointer[B] = {
        r1.foreach(p2.output)
        p2
    }
}
