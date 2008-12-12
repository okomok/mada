

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Implies extends Implies; trait Implies {
    class MadaImplies(_1: Boolean) {
        def implies(_2: => Boolean) = Implies_(_1, _2)
    }
    implicit def toMadaImplies(_1: Boolean): MadaImplies = new MadaImplies(_1)
}

object Implies_ {
    def apply(pre: Boolean, post: => Boolean): Boolean = !pre || post
}
