

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class FlatMap[A, +B](_1: Iterative[A], _2: A => Iterative[B]) extends Forwarder[B] {
    override protected lazy val delegate = _1.map(_2).flatten
}
