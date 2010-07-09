

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


package object dense extends dense.LiteralCommon with dense.OperatorCommon {

    @equivalentTo("new Nil{}")
    val Nil: Nil = _Dense.Nil

    @aliasOf("Cons")
    val :: = Cons

    @equivalentTo("x#addFirst[xs]")
    type ::[x <: Boolean, xs <: Dense] = xs#addFirst[x]

}
