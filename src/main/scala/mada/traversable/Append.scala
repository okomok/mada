

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Append[A](val left: Traversable[A], val right: Traversable[A]) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private var t = ^.left.start
        private var inLeft = true
        ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            ready
        }

        private def ready: Unit = {
            if (t.isEnd && inLeft) {
                t = ^.right.start
                inLeft = false
            }
        }
    }
}
