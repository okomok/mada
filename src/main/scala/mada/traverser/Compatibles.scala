

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


@compatibles
trait Compatibles { self: Traverser.type =>
//    implicit def madaTraverserFromSIterator[A](from: Iterator[A]): Traverser[A] = fromSIterator(from)
//    implicit def madaTraverserToSIterator[A](from: Traverser[A]): Iterator[A] = from.toSIterator
    implicit def madaTraverserToBoolean[A](from: Traverser[A]): Boolean = from.toBoolean
}
