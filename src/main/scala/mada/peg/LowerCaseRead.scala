

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg

case class LowerCaseRead(_1: Peg[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.readMap{ (v: sequence.Vector[Char]) => sequence.vector.lowerCase(v) }
}
