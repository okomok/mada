

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Adds reluctant methods for quantifiers.
 */
trait Quantifier[A] extends Peg[A] {
    /**
     * Stops including <code>that</code>.
     *
     * @see     >>> as alias.
     */
    def until(that: Peg[A]): Peg[A]

    /**
     * Alias of <code>until</code>
     */
    final def >>>(that: Peg[A]) = until(that)
}
