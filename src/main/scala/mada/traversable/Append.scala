

// Copy_2 Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Append[A](_1: Traversable[A], _2: Traversable[A]) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private var t = _1.begin
        private var inLeft = true
        ready

        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            t.++
            ready
        }

        private def ready: Unit = {
            if (!t && inLeft) {
                t = _2.begin
                inLeft = false
            }
        }
    }
}
