

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains implicit conversions around <code>Iterator</code>.
 */
trait Compatibles {
    import Iterables._
// from
  //  implicit def madaIteratorToJclEnumeration[A](from: Iterator[A]): java.util.Enumeration[A] = toJclEnumeration(from)
    implicit def madaIterableToJclIterable[A](from: Iterable[A]): java.lang.Iterable[A] = toJclIterable(from)
// to
  //  implicit def madaIteratorFromJclEnumeration[A](from: java.util.Enumeration[A]): Iterator[A] = fromJclEnumeration(from)
    implicit def madaIterableFromJclIterable[A](from: java.lang.Iterable[A]): Iterable[A] = fromJclIterable(from)
    implicit def madaIterableFromObjectInput(from: java.io.ObjectInput): Iterable[AnyRef] = fromObjectInput(from)
}
