

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


class ToSome[+A](val _1: Sequence[A]) extends Forwarder[A] {
    override protected val delegate = _1

    override def toSome = _1.toSome // toSome-toSome fusion
}

object ToSome {
    implicit def madaToSomeToSSequence[A](from: ToSome[A]): scala.collection.Sequence[A] = from.toSSequence
    implicit def madaToSomeToJIterable[A](from: ToSome[A]): java.lang.Iterable[A] = from.toJIterable
}
