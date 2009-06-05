

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class FlatMap[A, B](_1: Vector[A], _2: A => Vector[B]) extends Forwarder[B] {
    override protected val delegate = flatten(_1.map(_2))
}
