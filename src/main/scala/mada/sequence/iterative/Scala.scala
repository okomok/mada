

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative

/*
case class FromSIterable[+A](_1: Iterable[A]) extends Forwarder[A] {
    override protected val delegate: Iterative[A] = _1 match {
        case ToSIterable(from) => from // from-to fusion
        case _ => new _FromSIterable(_1)
    }

    override def toSIterable = _1 // to-from fusion
}*/

case class FromSIterable[+A](_1: Iterable[A]) extends Iterative[A] {
    override def begin = iterator.fromSIterator(_1.iterator)
}

case class ToSSequence[+A](_1: Iterative[A]) extends scala.collection.Sequence[A] {
    override def iterator = _1.begin.toSIterator
    override def apply(n: Int) = _1.at(n)
    override def length = _1.size
}
