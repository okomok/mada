

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Iterate[A](_1: A, _2: A => A) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private var acc = _1

        override def isEnd = false
        override def deref = acc
        override def increment = { acc = _2(acc) }
    }
}
