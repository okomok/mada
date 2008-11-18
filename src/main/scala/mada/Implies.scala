
package mada


object Implies {
    def apply(p: Boolean, q: => Boolean): Boolean = !p || q

    object Operator {
        class MadaImplies(l: Boolean) {
            def implies(r: => Boolean) = apply(l, r)
        }
        implicit def toMadaImplies(l: Boolean): MadaImplies = new MadaImplies(l)
    }
}
