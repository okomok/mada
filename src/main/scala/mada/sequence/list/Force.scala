

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Force[+A](_1: List[A]) extends Forwarder[A] {
    override protected val delegate = _1 // List is inherently forced?
}
