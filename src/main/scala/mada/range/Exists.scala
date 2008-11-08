
package mada.range


object Exists {
    def apply[A](r: Range[A], f: A => Boolean): Boolean = r.find(f) != None
}
