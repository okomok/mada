

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations


private[reactive]
class Common {

    @annotation.returnThat
    def from[A](to: Reactive[A]): Reactive[A] = to

    /**
     * The empty sequence
     */
    val empty: Reactive[Nothing] = Empty()

    /**
     * A single-element sequence
     */
    def single[A](x: A): Reactive[A] = Single(x)

    /**
     * An infinite sequence
     */
    def origin(k: (=> Unit) => Unit): Reactive[Unit] = Origin(k)

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

    @annotation.equivalentTo("continuations.reset(ctx(BlockEnv))")
    def block[A](ctx: BlockEnv.type => A @continuations.cpsParam[A, Any]): Unit = continuations.reset(ctx(BlockEnv))

    @annotation.equivalentTo("from(util.optional(body))")
    def optional[A](body: => A): Reactive[A] = from(util.optional(body))

    @annotation.equivalentTo("from(util.optionalErr(body))")
    def optionalErr[A](body: => A): Reactive[A] = from(util.optionalErr(body))

    @annotation.conversion
    def fromCps[A](from: => A @scala.util.continuations.suspendable): Reactive[A] = new FromCps(from)

}
