

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
class Common {

    @returnThat
    def from[A](to: Reactive[A]): Reactive[A] = to

    /**
     * The empty sequence
     */
    val empty: Reactive[Nothing] = Empty()

    /**
     * Calls all the functions in a Reactive sequence.
     */
    def reactions[A](fs: Reactive[A => Unit]): A => Unit = Reactions(fs)

    /**
     * Calls all the functions in a Reactive sequence.
     */
    def reactions[A](fs: Iterative[A => Unit]): A => Unit = Reactions(fs)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Reactive[A] = new FromSIterable(from)
    }

    @aliasOf("generator.Generator")
     val Generator = generator.Generator
    type Generator[+A] = generator.Generator[A]

}
