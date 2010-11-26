

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends reactive.Sequence[A] with Equals { // physical

    @annotation.conversion
    def asIterative: Iterative[A] // logical

    @annotation.conversion
    final def toIterative: Iterative[A] = asIterative // logical override

    override def asReactive: Reactive[A] = AsReactive(asIterative) // logical super

    @annotation.pre("Both sequences are finite if result is `true`.")
    override def equals(that: Any) = that match {
        case that: Sequence[_] => (that canEqual this) && asIterative.equalsIf(that.asIterative)(function.equal)
        case _ => false
    }

    override def canEqual(that: Any) = that.isInstanceOf[Sequence[_]]

    @annotation.pre("This sequence is finite.")
    override def hashCode = {
        var r = 1
        val it = asIterative.begin
        while (it) {
            r = 31 * r + (~it).hashCode
            it.++
        }
        r
    }

    @annotation.pre("This sequence is finite.")
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

// methodization
    sealed class _OfZip2[T1, T2](_this: Sequence[(T1, T2)]) {
        def map2[R](f: (T1, T2) => R): Iterative[R] = _this.asIterative.map{case (v1, v2) => f(v1, v2)}
    }
    implicit def _ofZip2[T1, T2](_this: Sequence[(T1, T2)]): _OfZip2[T1, T2] = new _OfZip2(_this)

    sealed class _OfZip3[T1, T2, T3](_this: Sequence[((T1, T2), T3)]) {
        def map3[R](f: (T1, T2, T3) => R = function.identity3[T1, T2, T3]): Iterative[R] = _this.asIterative.map{case ((v1, v2), v3) => f(v1, v2, v3)}
    }
    implicit def _ofZip3[T1, T2, T3](_this: Sequence[((T1, T2), T3)]): _OfZip3[T1, T2, T3] = new _OfZip3(_this)

    sealed class _OfZip4[T1, T2, T3, T4](_this: Sequence[(((T1, T2), T3), T4)]) {
        def map4[R](f: (T1, T2, T3, T4) => R = function.identity4[T1, T2, T3, T4]): Iterative[R] = _this.asIterative.map{case (((v1, v2), v3), v4) => f(v1, v2, v3, v4)}
    }
    implicit def _ofZip4[T1, T2, T3, T4](_this: Sequence[(((T1, T2), T3), T4)]): _OfZip4[T1, T2, T3, T4] = new _OfZip4(_this)

    sealed class _OfZip5[T1, T2, T3, T4, T5](_this: Sequence[((((T1, T2), T3), T4), T5)]) {
        def map5[R](f: (T1, T2, T3, T4, T5) => R = function.identity5[T1, T2, T3, T4, T5]): Iterative[R] = _this.asIterative.map{case ((((v1, v2), v3), v4), v5) => f(v1, v2, v3, v4, v5)}
    }
    implicit def _ofZip5[T1, T2, T3, T4, T5](_this: Sequence[((((T1, T2), T3), T4), T5)]): _OfZip5[T1, T2, T3, T4, T5] = new _OfZip5(_this)

}
