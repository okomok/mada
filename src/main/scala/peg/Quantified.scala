

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


/**
 * Adds reluctant methods for quantified peg.
 */
trait Quantified[-A] extends Peg[A] {
    /**
     * Stops including <code>that</code>.
     *
     * @see     >>> as alias.
     */
    def until[B <: A](that: Peg[B]): Peg[B]

    @aliasOf("until")
    final def >>>[B <: A](that: Peg[B]): Peg[B] = until(that)
}


trait QuantifiedForwarder[-A] extends Forwarder[A] with Quantified[A] {
    override protected def delegate: Quantified[A]

    override def until[B <: A](that: Peg[B]) = delegate.until(that)
}
