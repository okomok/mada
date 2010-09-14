

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends reactive.Sequence[A] { // physical

    @conversion
    def asIterative: Iterative[A] // logical

    override def asReactive: Reactive[A] = AsReactive(asIterative) // logical super

    /**
     * Compares the specified object with this sequence for equality.
     * Returns true if and only if the specified object is also a sequence,
     * both sequences have the same size, and all corresponding pairs of
     * elements in the two sequences are equal.
     * You shall not override this in a purpose except optimization.
     * (In this regard, JCL hierarchy is broken. E.g. <code>unmodifiableCollection</code> can't forward <code>equals</code>.
     * Probably <code>List/Set</code> shouldn't have been a subclass of <code>Collection</code>.)
     *
     * @see Effective Java 2nd Edition - Item 8
     */
    @pre("Both sequences are finite if result is `true`.")
    override def equals(that: Any) = that match {
        case that: Sequence[_] => asIterative.equalsIf(that.asIterative)(function.equal)
        case _ => false
    }

    /**
     * Returns a hash code of this sequence.
     */
    @pre("This sequence is finite.")
    override def hashCode = {
        var r = 1
        val it = asIterative.begin
        while (it) {
            r = 31 * r + (~it).hashCode
            it.++
        }
        r
    }

    /**
     * Returns a string representation of this sequence.
     */
    @pre("This sequence is finite.")
    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val it = asIterative.begin
        if (it) {
            sb.append(~it)
            it.++
        }
        while (it) {
            sb.append(", ")
            sb.append(~it)
            it.++
        }

        sb.append(']')
        sb.toString
    }

}


object Sequence {

    trait Forwarder[+A] extends Sequence[A] with reactive.Sequence.Forwarder[A] {
        override protected def delegate: Sequence[A]
        override def asIterative = delegate.asIterative
    }

// logical hierarchy
    implicit def _asReactive[A](from: Sequence[A]): Reactive[A] = from.asReactive

}
