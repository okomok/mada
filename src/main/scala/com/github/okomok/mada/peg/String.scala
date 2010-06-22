

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private[mada] case class FromChar(_1: Char) extends Forwarder[Char] {
    override protected val delegate = single(_1)
}


private[mada] case class Unstringize(_1: String) extends Forwarder[Char] {
    override protected val delegate = unstringizeBy(_1)(function.equal)
}

private[mada] case class UnstringizeBy(_1: String, _2: (Char, Char) => Boolean) extends Forwarder[Char] {
    override protected val delegate = fromSequenceBy(sequence.vector.unstringize(_1))(_2)
}
