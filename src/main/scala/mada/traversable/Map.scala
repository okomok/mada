

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Map[A, B](val _1: Traversable[A], val _2: A => B) extends Traversable[B] { self =>
    override def start = _1 match {
        case _1: Map[_, _] => _1._1.map(_2 compose _1._2).start // map-map fusion
        case _ => _start
    }

    private def _start = new Traverser[B] {
        private val t = self._1.start
        override def isEnd = t.isEnd
        override def deref = self._2(t.deref)
        override def increment = t.increment
    }
}
