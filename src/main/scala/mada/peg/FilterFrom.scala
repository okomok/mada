

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class FilterFrom[A](_1: Peg[A], _2: Vector[A]) extends sequence.Forwarder[A] {
    override protected val delegate = _1.tokenize(_2).map{ w => w.asSequence }.flatten
}
