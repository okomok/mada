

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Filter[A](val _1: Traversable[A], val _2: A => Boolean) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private val t = self._1.start
        ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            ready
        }

        private def ready: Unit = {
            while (!t.isEnd && !self._2(t.deref)) {
                t.increment
            }
        }
    }

    override def filter(p: A => Boolean) = _1.filter{ e => _2(e) && p(e) } // filter-filter fusion
}
