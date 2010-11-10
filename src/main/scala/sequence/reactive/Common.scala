

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations


private[reactive]
class Common {

    @returnThat
    def from[A](to: Reactive[A]): Reactive[A] = to

    /**
     * The empty sequence
     */
    val empty: Reactive[Nothing] = Empty()

    /**
     * An infinite sequence
     */
    def infinite: Reactive[Unit] = Infinite()

    /**
     * A (possibly) parallel infinite sequence
     */
    def parallel: Reactive[Unit] = Parallel()

    /**
     * Turns into a by-name expression.
     */
    def byName[A](r: => Reactive[A]): Reactive[A] = ByName(r)

    /**
     * Calls all the functions in a Reactive sequence.
     */
    def multi[A](fs: Reactive[A => Unit]): A => Unit = Multi(fs)

    /**
     * Calls all the functions in a Iterative sequence.
     */
    def multi[A](fs: Iterative[A => Unit]): A => Unit = Multi(fs)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Reactive[A] = from
    }

    @aliasOf("scala.util.continuations.reset[A, Any]")
    def block[A](ctx: => (A @continuations.cpsParam[A, Any])): Unit = continuations.reset(ctx)

}
