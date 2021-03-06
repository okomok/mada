

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class LowerCaseRead(_1: Peg[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.readMap{ (v: sequence.Vector[Char]) => v.lowerCase }
}
