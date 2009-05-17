

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class FromSIterable[+A](_1: Iterable[A]) extends Forwarder[A] {
    override protected val delegate: Traversable[A] = _1 match {
        case ToSIterable(from) => from // from-to fusion
        case _ => new _FromSIterable(_1)
    }

    override def toSIterable = _1 // to-from fusion
}

private class _FromSIterable[+A](_1: Iterable[A]) extends Traversable[A] {
    override def begin = traverser.fromSIterator(_1.elements)
}


case class ToSIterable[+A](_1: Traversable[A]) extends Iterable[A] {
    override def elements = _1.begin.toSIterator
}
