

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

    /**
     * @return  <code>new Nil{}</code>.
     */
    val Nil = NilWrap.value

    /**
     * Alias of <code>Cons</code>
     */
    type ::[h, t <: List] = Cons[h, t]

    /**
     * @return  <code>r#prepend[l]</code>.
     */
    type :::[l <: List, r <: List] = List.:::[l, r]

    /**
     * @return  <code>r#reversePrepend[l]</code>.
     */
    type reverse_:::[l <: List, r <: List] = List.reverse_:::[l, r]

}
