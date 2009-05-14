

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Single[A](_1: A) extends Traversable[A] {
    override def start = new Traverser[A] {
        private var ends = false
        override def isEnd = ends
        override def deref = { preDeref; _1 }
        override def increment = { preIncrement; ends = true }
    }
}
