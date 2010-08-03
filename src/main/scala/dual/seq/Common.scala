

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


trait Common {

    /**
     * Makes a Seq Equiv from element Equiv.
     */
     def eqv[ee <: Equiv](ee: ee): eqv[ee] = new Eqv(ee)
    type eqv[ee <: Equiv] = Eqv[ee]

    @equivalentTo("new Nil{}")
     val Nil = _Nil.value

    @equivalentTo("x# ::[xs]")
    type ::[x <: Any, xs <: Seq] = xs# ::[x]

    @equivalentTo("xs# ++[ys]")
    type ++[xs <: Seq, ys <: Seq] = xs# ++[ys]

    @equivalentTo("Nil.::(x)")
     def single[x <: Any](x: x): single[x] = Nil. ::(x)
    type single[x <: Any]                  = Nil# ::[x]

}
