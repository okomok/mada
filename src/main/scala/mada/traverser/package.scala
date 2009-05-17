

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traverser {


// aliases

    @aliasOf("Traversable")
    type Type[+A] = Traverser[A]

    @aliasOf("Traversable")
    val compatibles: Compatibles = Traverser


// methods

    /**
     * The universal end traverser
     */
    val theEnd: Traverser[Nothing] = TheEnd


// conversions

    @returnThat
    def from[A](to: Traverser[A]) = to

    @conversion
    def fromSIterator[A](from: Iterator[A]): Traverser[A] = FromSIterator(from)

    @conversion
    def fromJIterator[A](from: java.util.Iterator[A]): Traverser[A] = FromJIterator(from)

    @conversion
    final def toJIterator[A](from: Traverser[A]): java.util.Iterator[A] = ToJIterator(from) // invariant can't be method.

}
