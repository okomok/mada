

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


/**
 * Adds reluctant methods for quantified peg.
 */
trait Quantified[A] extends Peg[A] {
    /**
     * Stops including <code>that</code>.
     *
     * @see     >>> as alias.
     */
    def until(that: Peg[A]): Peg[A]

    @aliasOf("until")
    final def >>>(that: Peg[A]): Peg[A] = until(that)
}


trait QuantifiedForwarder[A] extends Forwarder[A] with Quantified[A] {
    override protected def delegate: Quantified[A]

    override def until(that: Peg[A]) = delegate.until(that)
}
