

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Map[A, B](val _1: Traversable[A], val _2: A => B) extends Traversable[B] { self =>
    override def start = new Traverser[B] {
        private val t = self._1.start
        override def isEnd = t.isEnd
        override def deref = self._2(t.deref)
        override def increment = t.increment
    }

    override def map[C](f: B => C) = _1.map(f compose _2) // map-map fusion
}
