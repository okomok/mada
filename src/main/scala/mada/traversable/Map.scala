

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Map[A, B](that: Traversable[A], function: A => B) extends Traversable[B] { ^ =>
    override def start = new Traverser[B] {
        private val t = ^.that.start
        override def isEnd = t.isEnd
        override def deref = ^.function(t.deref)
        override def increment = t.increment
    }
}
