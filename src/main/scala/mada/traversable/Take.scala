

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Take[A](val _1: Traversable[A], val _2: Int) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private var t = self._1.start
        private var i = self._2
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

    override def take(n: Int) = _1.take(Math.max(_2, n)) // take-take fusion
}
