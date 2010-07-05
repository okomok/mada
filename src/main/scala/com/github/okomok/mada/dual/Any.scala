

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Any
 */
trait Any {
     def asInstanceOfBoolean: asInstanceOfBoolean = unsupported
    type asInstanceOfBoolean <: Boolean

     def asInstanceOfNat: asInstanceOfNat = unsupported
    type asInstanceOfNat <: Nat

     def asInstanceOfList: asInstanceOfList = unsupported
    type asInstanceOfList <: List

     def undual: undual = unsupported
    type undual <: scala.Any

    override def hashCode = undual.hashCode
    override def toString = undual.toString
}


object Any {
    // implicit def _boxing[A](from: A): Any = Box(from)
}
