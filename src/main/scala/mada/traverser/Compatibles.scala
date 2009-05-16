

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


@compatibles
trait Compatibles { self: Traverser.type =>
//    implicit def madaTraverserFromIterator[A](from: Iterator[A]): Traverser[A] = fromIterator(from)
//    implicit def madaTraverserToIterator[A](from: Traverser[A]): Iterator[A] = from.toIterator
    implicit def madaTraverserToBoolean[A](from: Traverser[A]): Boolean = from.toBoolean
}
