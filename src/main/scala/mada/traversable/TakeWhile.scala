

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class TakeWhile[A](_1: Traversable[A], _2: A => Boolean) extends Traversable[A] {
    override def start = new Traverser[A] {
        private var t = _1.start
        ready

        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            t.++
            ready
        }

        private def ready: Unit = {
            if (t && !_2(~t)) {
                t = traverser.theEnd
            }
        }
    }
}
