

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class AsIterative[A](_1: Vector[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private var i = _1.start
        override def isEnd = i == _1.end
        override def deref = { preDeref; _1(i) }
        override def increment = { preIncrement; i += 1 }
    }

    // TODO: override optimizable methods.
    override def isEmpty = _1.isEmpty
}
