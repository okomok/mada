

// Copy_2 Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Append[A](val _1: Traversable[A], val _2: Traversable[A]) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private var t = self._1.start
        private var inLeft = true
        ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            ready
        }

        private def ready: Unit = {
            if (t.isEnd && inLeft) {
                t = self._2.start
                inLeft = false
            }
        }
    }
}
