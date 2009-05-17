

// Copy_2 Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Append[+A](_1: Sequence[A], _2: Sequence[A]) extends Sequence[A] {
    override def begin = new Iterator[A] {
        private var it = _1.begin
        private var inLeft = true
        ready

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            it.++
            ready
        }

        private def ready: Unit = {
            if (!it && inLeft) {
                it = _2.begin
                inLeft = false
            }
        }
    }
}
