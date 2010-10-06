

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[A] extends iterative.Sequence[A] { // physical

    @conversion
    def asVector: Vector[A] // logical

    override def asIterative: Iterative[A] = AsIterative(asVector) // logical super

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: Sequence[_] => (that canEqual this) && asVector.equalsIf(that.asVector)(function.equal)
        case _ => super.equals(that)
    }

    @optimize
    override def hashCode: Int = {
        val v = asVector
        var r = 1
        var i = v.start; val j = v.end
        while (i != j) {
            r = 31 * r + v(i).hashCode
            i += 1
        }
        r
    }

    @optimize
    override def toString = asVector.toJList.toString

}


object Sequence {

    trait Forwarder[A] extends Sequence[A] with iterative.Sequence.Forwarder[A] {
        override protected def delegate: Sequence[A]
        override def asVector = delegate.asVector
    }

// logical hierarchy
    implicit def _asIterative[A](from: Sequence[A]): Iterative[A] = from.asIterative

// methodization
    sealed class _OfPair[A, B](_this: Sequence[(A, B)]) {
        def map2[C](f: (A, B) => C): Vector[C] = _this.asVector.map{case (a, b) => f(a, b)}
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this)

}
