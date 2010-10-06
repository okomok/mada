

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

// methodization
    sealed class _OfPair[A, B](_this: Sequence[(A, B)]) {
        def map2[C](f: (A, B) => C): Reactive[C] = _this.asReactive.map{case (a, b) => f(a, b)}
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this)

}
