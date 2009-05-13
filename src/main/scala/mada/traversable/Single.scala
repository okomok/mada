

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Single[A](val element: A) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private var ends = false
        override def isEnd = ends
        override def deref = { preDeref; ^.element }
        override def increment = { preIncrement; ends = true }
    }
}
