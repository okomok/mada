

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class TakeWhile[A](val _1: Traversable[A], val _2: A => Boolean) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private var t = self._1.start
        ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            ready
        }

        private def ready: Unit = {
            if (!t.isEnd && !self._2(t.deref)) {
                t = traverser.theEnd
            }
        }
    }
}
