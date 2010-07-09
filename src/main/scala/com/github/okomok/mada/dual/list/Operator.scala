

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


/**
 * Contains operators for List.
 */
object Operator extends OperatorCommon


trait OperatorCommon {

    @equivalentTo("new Nil{}")
    val Nil: Nil = _List.Nil

    @aliasOf("Cons")
    val :: = Cons

    @equivalentTo("x#addFirst[xs]")
    type ::[x <: Any, xs <: List] = xs#addFirst[x]

    @equivalentTo("ys#prepend[xs]")
    type :::[xs <: List, ys <: List] = ys#prepend[xs]

    @equivalentTo("ys#reversePrepend[xs]")
    type reverse_:::[xs <: List, ys <: List] = ys#prependReversed[xs]

}
