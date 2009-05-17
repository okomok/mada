

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class FromIterable[A](_1: Iterable[A]) extends Forwarder[A] {
    override protected val delegate: Traversable[A] = _1 match {
        case ToIterable(from) => from // from-to fusion
        case _ => new _FromIterable(_1)
    }

    override def toIterable = _1 // to-from fusion
}

private class _FromIterable[A](_1: Iterable[A]) extends Traversable[A] {
    override def begin = traverser.fromIterator(_1.elements)
}


case class ToIterable[A](_1: Traversable[A]) extends Iterable[A] {
    override def elements = _1.begin.toIterator
}
