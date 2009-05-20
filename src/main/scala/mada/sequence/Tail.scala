

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Tail[+A](_1: Sequence[A]) extends Forwarder[A] {
    override protected val delegate = _1.drop(1)
}
