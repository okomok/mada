

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class FromIterable[A](that: Iterable[A]) extends Traversable[A] {
    override def start = traverser.fromIterator(that.elements)
}
