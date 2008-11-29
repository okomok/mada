
package mada


object Implies extends Implies; trait Implies {
    class MadaImplies(_1: Boolean) {
        def implies(_2: => Boolean) = ImpliesImpl(_1, _2)
    }
    implicit def toMadaImplies(_1: Boolean): MadaImplies = new MadaImplies(_1)
}

object ImpliesImpl {
    def apply(pre: Boolean, post: => Boolean): Boolean = !pre || post
}
