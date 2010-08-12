

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


object Peg extends Common


trait Peg extends Any with ReferenceEquality {
    type self <: Peg
    type asInstanceOfPeg = self

    /**
     * The parse method
     */
     def parse[xs <: List](xs: xs): parse[xs]
    type parse[xs <: List] <: Result

    /**
     * Returns a mathced width.
     */
     def width: width = unsupported("Peg.width is not constant.")
    type width <: Nat

    /**
     * Sequence
     */
     def seq[that <: Peg](that: that): seq[that]
    type seq[that <: Peg] <: Peg

    /**
     * Ordered choice
     */
     def or[that <: Peg](that: that): or[that]
    type or[that <: Peg] <: Peg

    /**
     * Zero-or-more
     */
     def star: star
    type star <: Peg

    /**
     * One-or-more
     */
     def plus: plus
    type plus <: Peg

    /**
     * Optional
     */
     def opt: opt
    type opt <: Peg

    /**
     * And-predicate
     */
     def and: and
    type and <: Peg

    /**
     * Not-predicate
     */
     def not: not
    type not <: Peg
}
