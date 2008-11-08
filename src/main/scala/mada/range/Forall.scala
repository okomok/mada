
package mada.range


object Forall {
    def apply[A](r: Range[A], f: A => Boolean): Boolean = r.find(!f(_)) == None
}
