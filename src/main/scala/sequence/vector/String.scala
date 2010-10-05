

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private
case class Unstringize(_1: String) extends Vector[Char] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1.charAt(i)

    override def force = this
    override def stringize(implicit pre: Vector[Char] <:< Vector[Char]) = _1 // to-from fusion
}


private
case class LowerCase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.map(java.lang.Character.toLowerCase(_))
}

private
case class UpperCase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.map(java.lang.Character.toUpperCase(_))
}
