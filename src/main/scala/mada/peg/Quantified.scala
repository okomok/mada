

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


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


case class Opt[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    override protected val delegate = _1.repeat(0, 1)
}

case class Plus[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    throwIfZeroWidth(_1, "plus")
    override protected val delegate = _1.repeat(1, ())
}

case class Star[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    throwIfZeroWidth(_1, "star")
    override protected val delegate = _1.repeat(0, ())
}
