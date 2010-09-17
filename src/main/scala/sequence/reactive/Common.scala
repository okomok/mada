

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
class Common {


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
     * Creates an infinite sequence repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Reactive[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Reactive[A] = Repeat(e)

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
