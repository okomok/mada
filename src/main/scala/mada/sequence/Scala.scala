

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class FromSIterable[+A](_1: Iterable[A]) extends Forwarder[A] {
    override protected val delegate: Sequence[A] = _1 match {
        case ToSIterable(from) => from // from-to fusion
        case _ => new _FromSIterable(_1)
    }

    override def toSIterable = _1 // to-from fusion
}

private class _FromSIterable[+A](_1: Iterable[A]) extends Sequence[A] {
    override def begin = iterator.fromSIterator(_1.elements)
}


case class ToSIterable[+A](_1: Sequence[A]) extends Iterable[A] {
    override def elements = _1.begin.toSIterator
}
