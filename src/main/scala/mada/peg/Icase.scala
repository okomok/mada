

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Icase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = fromSequence(vector.lowerCase(_1)).lowerCaseRead
}
