

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Append[A](left: Traversable[A], right: Traversable[A]) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private var t = ^.left.start
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            if (t.isEnd) {
                t = ^.right.start
            }
        }
    }
}
