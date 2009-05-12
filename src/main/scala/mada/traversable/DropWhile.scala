

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class DropWhile[A](that: Traversable[A], predicate: A => Boolean) extends Traversable[A] { ^ =>
    override def start = {
        val t = that.start
        while (!t.isEnd && predicate(t.deref)) {
            t.increment
        }
        t
    }
}
