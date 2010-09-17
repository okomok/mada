

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] { // physical

    @conversion
    def asReactive: Reactive[A] // logical

}


object Sequence {

    trait Forwarder[+A] extends Sequence[A] with util.Forwarder {
        override protected def delegate: Sequence[A]
        override def asReactive = delegate.asReactive
    }

}
