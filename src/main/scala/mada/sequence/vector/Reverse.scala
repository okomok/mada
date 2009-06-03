

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Reverse[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = _1.permutation{ i => _1.size - i - 1 }
    override def reverse = _1 // reverse-reverse fusion
}
