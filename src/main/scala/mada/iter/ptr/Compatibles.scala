

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.ptr


/**
 * Contains implicit conversions around <code>Pointer</code>.
 */
private[mada] trait Compatibles {
    import Pointer._

    implicit def madaPointerFromIterator[A](from: Iterator[A]): Pointer[A] = fromIterator(from)
    implicit def madaPointerToIterator[A](from: Pointer[A]): Iterator[A] = toIterator(from)
}
