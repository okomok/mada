

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Filter[A](val that: Traversable[A], val predicate: A => Boolean) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private val t = ^.that.start
        ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = ready

        private def ready: Unit = {
            while (!t.isEnd && !(^.predicate(t.deref))) {
                t.increment
            }
        }
    }
}
