

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Flatten[A](_1: Traversable[Traversable[A]]) extends Traversable[A] {
    override def start = new Traverser[A] {
        private val tt = _1.start
        private var t = ready
        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            t.++
            if (!t) {
                tt.++
                t = ready
            }
        }

        private def ready: Traverser[A] = {
            while (tt) {
                val u = (~tt).start
                if (u) {
                    return u
                }
                tt.++
            }
            traverser.theEnd
        }
    }
}
