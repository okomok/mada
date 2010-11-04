

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends iterative.Sequence[A] { // physical

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

    trait Forwarder[+A] extends Sequence[A] with iterative.Sequence.Forwarder[A] {
        override protected def delegate: Sequence[A]
        override def asVector = delegate.asVector
    }

// logical hierarchy
    implicit def _asIterative[A](from: Sequence[A]): Iterative[A] = from.asIterative

// methodization
    sealed class _OfZip2[T1, T2](_this: Sequence[(T1, T2)]) {
        def map2[R](f: (T1, T2) => R): Vector[R] = _this.asVector.map{case (v1, v2) => f(v1, v2)}
    }
    implicit def _ofZip2[T1, T2](_this: Sequence[(T1, T2)]): _OfZip2[T1, T2] = new _OfZip2(_this)

    sealed class _OfZip3[T1, T2, T3](_this: Sequence[((T1, T2), T3)]) {
        def map3[R](f: (T1, T2, T3) => R = function.identity3[T1, T2, T3]): Vector[R] = _this.asVector.map{case ((v1, v2), v3) => f(v1, v2, v3)}
    }
    implicit def _ofZip3[T1, T2, T3](_this: Sequence[((T1, T2), T3)]): _OfZip3[T1, T2, T3] = new _OfZip3(_this)

    sealed class _OfZip4[T1, T2, T3, T4](_this: Sequence[(((T1, T2), T3), T4)]) {
        def map4[R](f: (T1, T2, T3, T4) => R = function.identity4[T1, T2, T3, T4]): Vector[R] = _this.asVector.map{case (((v1, v2), v3), v4) => f(v1, v2, v3, v4)}
    }
    implicit def _ofZip4[T1, T2, T3, T4](_this: Sequence[(((T1, T2), T3), T4)]): _OfZip4[T1, T2, T3, T4] = new _OfZip4(_this)

    sealed class _OfZip5[T1, T2, T3, T4, T5](_this: Sequence[((((T1, T2), T3), T4), T5)]) {
        def map5[R](f: (T1, T2, T3, T4, T5) => R = function.identity5[T1, T2, T3, T4, T5]): Vector[R] = _this.asVector.map{case ((((v1, v2), v3), v4), v5) => f(v1, v2, v3, v4, v5)}
    }
    implicit def _ofZip5[T1, T2, T3, T4, T5](_this: Sequence[((((T1, T2), T3), T4), T5)]): _OfZip5[T1, T2, T3, T4, T5] = new _OfZip5(_this)

}
