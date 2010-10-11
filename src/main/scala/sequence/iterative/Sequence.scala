

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends reactive.Sequence[A] with Equals { // physical

    @conversion
    def asIterative: Iterative[A] // logical

    override def asReactive: Reactive[A] = AsReactive(asIterative) // logical super

    @pre("Both sequences are finite if result is `true`.")
    override def equals(that: Any) = that match {
        case that: Sequence[_] => (that canEqual this) && asIterative.equalsIf(that.asIterative)(function.equal)
        case _ => false
    }

    override def canEqual(that: Any) = that.isInstanceOf[Sequence[_]]

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
