
package mada


object Implies {
    def apply(p: Boolean, q: => Boolean): Boolean = !p || q
}
