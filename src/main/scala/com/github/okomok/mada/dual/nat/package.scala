

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


package object nat {


// Peano

    @equivalentTo("new peano.Zero{}")
    val Zero = _Peano.Zero

    private[mada] val Singular = _Peano.Singular


// Dense

    @equivalentTo("new dense.Nil{}")
    val Nil: Nil = _Dense.Nil

    @equivalentTo("x#addFirst[xs]")
    type ::[x <: Boolean, xs <: Dense] = xs#addFirst[x]

}
