

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Zip[A, B](_1: Traversable[A], _2: Traversable[B]) extends Traversable[(A, B)] {
    override def start = new Traverser[(A, B)] {
        private val t1 = _1.start
        private val t2 = _2.start
        override def isEnd = !t1 || !t2
        override def deref = { preDeref; (~t1, ~t2) }
        override def increment = { preIncrement; t1.++; t2.++ }
    }
}
