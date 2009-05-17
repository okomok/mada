

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object iterator {


// aliases

    @aliasOf("Sequence")
    type Type[+A] = Iterator[A]

    @aliasOf("Sequence")
    val compatibles: Compatibles = Iterator


// methods

    /**
     * The universal end iterator
     */
    val theEnd: Iterator[Nothing] = TheEnd


// conversions

    @returnThat
    def from[A](to: Iterator[A]) = to

    @conversion
    def fromSIterator[A](from: scala.Iterator[A]): Iterator[A] = FromSIterator(from)

    @conversion
    def fromJIterator[A](from: java.util.Iterator[A]): Iterator[A] = FromJIterator(from)

    @conversion
    final def toJIterator[A](from: Iterator[A]): java.util.Iterator[A] = ToJIterator(from) // invariant can'it be method.

}
