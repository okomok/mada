

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


package object reactive {


    @aliasOf("Reactive")
    val Type = Reactive

    @aliasOf("Reactive")
    type Type[+A] = Reactive[A]


// constructors

    /**
     * The empty sequence
     */
    val empty: Reactive[Nothing] = Empty()

    /**
     * A sequence with a single element.
     */
    def single[A](e: A): Reactive[A] = Single(e)
    /**
     * Unfolds right-associative.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Reactive[B] = UnfoldRight(z, op)


// pseudo

    /**
     * Constructs a pseudo try-catch expression.
     */
    def `try`[A](r: Reactive[A]): Try[A] = Try(r)


// conversion

    @returnThat
    def from[A](to: Reactive[A]): Reactive[A] = to

    @conversion
    def fromIterative[A](from: iterative.Sequence[A]): Reactive[A] = FromIterative(from.asIterative)

    @conversion
    def fromSIterable[A](from: Iterable[A]): Reactive[A] = FromSIterable(from)

}
