
package mada


object Implies {
    def apply(p: Boolean, q: => Boolean): Boolean = !p || q

    object Operator {
        class MadaImpliesLeft(l: Boolean) {
            def implies(r: => Boolean) = apply(l, r)
        }
        implicit def toMadaImpliesLeft(l: Boolean) = new MadaImpliesLeft(l)
    }
}
