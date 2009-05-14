

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Zip[A, B](val _1: Traversable[A], val _2: Traversable[B]) extends Traversable[(A, B)] { self =>
    override def start = new Traverser[(A, B)] {
        private val t1 = self._1.start
        private val t2 = self._2.start
        override def isEnd = t1.isEnd || t2.isEnd
        override def deref = { preDeref; (t1.deref, t2.deref) }
        override def increment = { preIncrement; t1.increment; t2.increment }
    }
}
