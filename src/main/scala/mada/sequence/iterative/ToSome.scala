

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


// will be rejected.


class ToSome[+A](val _1: Iterative[A]) extends Forwarder[A] {
    override protected val delegate = _1

    override def toSome: ToSome[A] = this // toSome-toSome fusion
}

object ToSome {
    implicit def madaToSomeToSSequence[A](from: ToSome[A]): scala.collection.Sequence[A] = from.toSSequence
    implicit def madaToSomeToJIterable[A](from: ToSome[A]): java.lang.Iterable[A] = from.toJIterable
}
