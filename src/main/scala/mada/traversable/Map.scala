

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Map[A, B](val that: Traversable[A], val function: A => B) extends Traversable[B] { ^ =>
    override def start = that match {
        case that: Map[_, _] => that.that.map(function compose that.function).start // map-map fusion
        case _ => _start
    }

    private def _start = new Traverser[B] {
        private val t = ^.that.start
        override def isEnd = t.isEnd
        override def deref = ^.function(t.deref)
        override def increment = t.increment
    }
}
