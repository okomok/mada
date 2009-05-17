

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Take[+A](_1: Traversable[A], _2: Int) extends Traversable[A] {
    throwIfNegative(_2, "take")

    override def begin = new Traverser[A] {
        private var t = _1.begin
        private var i = _2
        ready

        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            t.++
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
