

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Filter[A](_1: Traversable[A], _2: A => Boolean) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private val t = self._1.start
        ready
        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            t.++
            ready
        }

        private def ready: Unit = {
            while (t && !self._2(~t)) {
                t.++
            }
        }
    }

    override def filter(p: A => Boolean) = _1.filter{ e => _2(e) && p(e) } // filter-filter fusion
}
