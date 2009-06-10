

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class ByLazy[+A](_1: function.OfLazy[List[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
