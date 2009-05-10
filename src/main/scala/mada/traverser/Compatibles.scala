

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


@provider @compatibles
trait Compatibles { self: Traverser.type =>
    implicit def madaTraverserFromIterator[A](from: Iterator[A]): Iterator[A] = fromIterator(from)
    implicit def madaTraverserToIterator[A](from: Traverser[A]): Iterator[A] = toIterator(from)
}
