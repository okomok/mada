

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object blend {


// general

    /**
     * Conditional compilation based on meta boolean value.
     */
    def `if`[A, b <: meta.Boolean](block: => A)(implicit _if: If[A, b]): Then[A] = _if(block)

    /**
     * Conditional compilation based on meta boolean value.
     */
    def doIf[b <: meta.Boolean](block: => Unit)(implicit _doIf: DoIf[b]): Unit = _doIf(block)

    /**
     * Repeats <code>n</code> times.
     */
    def times[n <: meta.Nat](op: => Unit)(implicit _times: Times[n]): Unit = _times(_ => op, 0)

    /**
     * Repeats <code>n</code> times.
     */
    def timesBy[n <: meta.Nat](op: Int => Unit)(implicit _times: Times[n]): Unit = _times(op, 0)

    /**
     * Returns true iif A type is the same as B type.
     */
    def isSame[A, B](implicit _isSame: IsSame[A, B] = IsSame._ofNotSame[A, B]): Boolean = _isSame.apply


// list

    @equivalentTo("new Nil{}")
    val Nil = NilWrap.value

    @equivalentTo("t#addFirst[h]")
    type ::[h, t <: List] = t#addFirst[h]

    @equivalentTo("r#prepend[l]")
    type :::[l <: List, r <: List] = r#prepend[l]

    @equivalentTo("r#reversePrepend[l]")
    type reverse_:::[l <: List, r <: List] = r#prependReversed[l]

}
