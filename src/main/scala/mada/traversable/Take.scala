

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Take[A](that: Traversable[A], count: Int) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private var t = ^.that.start
        private var i = count
        ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = {
            t.increment
            i -= 1
            ready
        }

        private def ready: Unit = {
            if (i == 0) {
                t = traverser.theEnd
            }
        }
    }
}
