

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


final class Map[A, B](val _1: Traversable[A], val _2: A => B) extends Traversable[B] { self =>
    override def start = new Traverser[B] {
        private val t = self._1.start
        override def isEnd = !t
        override def deref = self._2(~t)
        override def increment = t.++
    }

    override def map[C](f: B => C) = _1.map(f compose _2) // map-map fusion
}
