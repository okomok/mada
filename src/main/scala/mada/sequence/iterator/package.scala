

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object iterator {


// aliases

    @aliasOf("Iterator")
    type Type[+A] = Iterator[A]


// methods

    /**
     * The universal end iterator
     */
    val theEnd: Iterator[Nothing] = TheEnd


// conversion

    @returnThat
    def from[A](to: Iterator[A]) = to

    @conversion
    def fromSIterator[A](from: scala.Iterator[A]): Iterator[A] = FromSIterator(from)

    @conversion
    def fromJIterator[A](from: java.util.Iterator[A]): Iterator[A] = FromJIterator(from)

    @conversion
    def toJIterator[A](from: Iterator[A]): java.util.Iterator[A] = ToJIterator(from) // invariant can't be method.

}
