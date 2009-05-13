

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Flatten[A](val that: Traversable[Traversable[A]]) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private val tt = ^.that.start
        private var t = ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            if (t.isEnd) {
                tt.increment
                t = ready
            }
        }

        private def ready: Traverser[A] = {
            while (!tt.isEnd) {
                val u = tt.deref.start
                if (!u.isEnd) {
                    return u
                }
                tt.++
            }
            traverser.theEnd
        }
    }
}
