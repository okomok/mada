

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private[iterative]
class Common {

    @returnThat
    def from[A](to: Iterative[A]): Iterative[A] = to

    @aliasOf("Of.apply")
    def apply[A](from: A*): Iterative[A] = Of.apply(from: _*)

    @aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Of.unapplySeq(from)

    /**
     * The empty sequence
     */
    val empty: Iterative[Nothing] = Empty()

    /**
     * A sequence with a single element.
     */
    def single[A](e: A): Iterative[A] = Single(e)

    /**
     * Refers a sequence by lazy.
     */
    def byLazy[A](it: => Iterative[A]): Iterative[A] = ByLazy(it)

    /**
     * Refers a sequence by name.
     */
    def byName[A](it: => Iterative[A]): Iterative[A] = ByName(it)

    /**
     * Creates an infinite sequence repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterative[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Iterative[A] = Repeat(e)

    /**
     * Unfolds right-associative.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterative[B] = UnfoldRight(z, op)

    /**
     * Creates a sequence starting from <code>it<code>.
     */
    def bind[A](it: Iterator[A]): Iterative[A] = Bind(it)

    /**
     * Creates a sequence starting from <code>it<code>, which is evaluated by-name.
     */
    def bindName[A](it: => Iterator[A]): Iterative[A] = BindName(it)

    /**
     * Constructs a sequence from `Yield` expressions.
     */
    def generator[A](op: Yield[A] => Unit): Iterative[A] = Generator(op)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Iterative[A] = Iterative.from(from)
        def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Some(from.toSeq)
    }

}
