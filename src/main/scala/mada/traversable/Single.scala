

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


final class Single[A](val _1: A) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private var ends = false
        override def isEnd = ends
        override def deref = { preDeref; self._1 }
        override def increment = { preIncrement; ends = true }
    }
}
