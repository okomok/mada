

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


private[generator]
class Common {

    @returnThat
    def from[A](to: Generator[A]): Generator[A] = to

    /**
     * The empty sequence
     */
    val empty: Generator[Nothing] = Empty()

    /**
     * A sequence with a single element.
     */
    def single[A](e: A): Generator[A] = Single(e)

    /**
     * Creates an `Int` sequence from `n` to `m`.
     */
    def range(n: Int, m: Int): Generator[Int] = Range(n, m)

    /**
     * Creates an infinite sequence repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Generator[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Generator[A] = Repeat(e)

    /**
     * Unfolds right-associative.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Generator[B] = UnfoldRight(z, op)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Reactive[A] = new FromSIterable(from)
    }

}
