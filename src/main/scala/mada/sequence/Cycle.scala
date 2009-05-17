

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Cycle[+A](_1: Sequence[A]) extends Forwarder[A] {
    override protected lazy val delegate = {
        if (_1.isEmpty) {
            throw new UnsupportedOperationException("cycle on empty sequence")
        }
        repeat(()).flatMap{ (u: Unit) => _1 }
    }
}
