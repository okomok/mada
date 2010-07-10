

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


import java.nio.channels.{Selector, SelectionKey}


private[mada] class Common {


// constructors

    @returnThat
    def from[A](to: Reactive[A]): Reactive[A] = to

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


// conversion

    @conversion
    def fromIterative[A](from: iterative.Sequence[A]): Reactive[A] = FromIterative(from.asIterative)

    @conversion
    def fromSIterable[A](from: Iterable[A]): Reactive[A] = FromSIterable(from)

}
