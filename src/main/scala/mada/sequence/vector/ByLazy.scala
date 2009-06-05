

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class ByLazy[A](_1: function.OfLazy[Vector[A]]) extends Forwarder[A] {
    override protected lazy val delegate = _1()
}
