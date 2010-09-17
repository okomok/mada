

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends Equals { // physical

    @conversion
    def asReactive: Reactive[A] // logical

    override def equals(that: Any) = that match {
        case that: Sequence[_] => (that canEqual this) && super.equals(that)
        case _ => false
    }
    override def canEqual(that: Any) = that.isInstanceOf[Sequence[_]]

}


object Sequence {

    trait Forwarder[+A] extends Sequence[A] with util.Forwarder {
        override protected def delegate: Sequence[A]

        override def equals(that: Any): Boolean = that match {
            case that: Sequence[_] => (that canEqual this) && delegate.equals(that)
            case _ => false
        }

        override def asReactive = delegate.asReactive
    }

}
