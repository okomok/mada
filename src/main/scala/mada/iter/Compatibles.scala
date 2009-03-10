

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains implicit conversions around <code>Iterator</code>.
 */
trait Compatibles {
    import Iterables._
    /*
// from
    implicit def madaIteratorToJclEnumeration[A](from: Iterator[A]): java.util.Enumeration[A] = toJclEnumeration(from)
    implicit def madaIteratorToJclIterator[A](from: Iterator[A]): java.util.Iterator[A] = toJclIterator(from)
// to
    implicit def madaIteratorFromJclEnumeration[A](from: java.util.Enumeration[A]): Iterator[A] = fromJclEnumeration(from)
    implicit def madaIteratorFromJclIterator[A](from: java.util.Iterator[A]): Iterator[A] = fromJclIterator(from)
    implicit def madaIteratorFromObjectInput(from: java.io.ObjectInput): Iterator[AnyRef] = fromObjectInput(from)
    */
}
