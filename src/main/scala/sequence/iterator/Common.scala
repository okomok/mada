

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterator


private[iterator]
class Common {

    @annotation.returnThat
    def from[A](to: Iterator[A]) = to

    /**
     * The universal end iterator
     */
    val end: Iterator[Nothing] = End()

    @annotation.conversion
    def fromSIterator[A](from: scala.Iterator[A]): Iterator[A] = FromSIterator(from)

    @annotation.conversion
    def fromJIterator[A](from: java.util.Iterator[A]): Iterator[A] = FromJIterator(from)

    @annotation.conversion
    def toJIterator[A](from: Iterator[A]): java.util.Iterator[A] = ToJIterator(from) // invariant can't be method.

}
