

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


case class Unstringize(_1: String) extends Vector[Char] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1.charAt(i)

    override def force = this
    override def _stringize(_this: Vector[Char]) = _1 // to-from fusion
}


case class LowerCase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.map(java.lang.Character.toLowerCase(_))
}

case class UpperCase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.map(java.lang.Character.toUpperCase(_))
}
