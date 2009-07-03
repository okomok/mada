

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


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


// list

    @equivalentTo("new Nil{}")
    val Nil = NilWrap.value

    @aliasOf("Cons")
    type ::[h, t <: List] = Cons[h, t]

    @equivalentTo("r#prepend[l]")
    type :::[l <: List, r <: List] = List.:::[l, r]

    @equivalentTo("r#reversePrepend[l]")
    type reverse_:::[l <: List, r <: List] = List.reverse_:::[l, r]

}
