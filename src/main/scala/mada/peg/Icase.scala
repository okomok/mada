

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Icase(_1: sequence.Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = fromSequence(_1.lowerCase).lowerCaseRead
}
