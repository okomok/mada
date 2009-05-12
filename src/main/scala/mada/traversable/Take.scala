

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Take[A](that: Traversable[A], count: Int) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private var t = ^.that.start
        private var i = 0
        _ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            i += 1
            _ready
        }

        private def _ready: Unit = {
            if (i == count) {
                t = traverser.theEnd
            }
        }
    }
}
