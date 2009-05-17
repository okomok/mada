

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterator


@compatibles
trait Compatibles { self: Iterator.type =>
//    implicit def madaIteratorFromSIterator[A](from: scala.Iterator[A]): Iterator[A] = fromSIterator(from)
//    implicit def madaIteratorToSIterator[A](from: Iterator[A]): scala.Iterator[A] = from.toSIterator
    implicit def madaIteratorToBoolean[A](from: Iterator[A]): Boolean = from.toBoolean
}
