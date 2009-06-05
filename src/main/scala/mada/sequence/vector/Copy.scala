

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Copy[A](_1: Vector[A]) extends Forwarder[A] {
    override protected lazy val delegate = fromArray(_1.toArray)
}
