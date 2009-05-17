

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Cycle[+A](_1: Traversable[A]) extends Forwarder[A] {
    override protected lazy val delegate = {
        if (_1.isEmpty) {
            throw new UnsupportedOperationException("cycle on empty traversable")
        }
        repeat(()).flatMap{ (u: Unit) => _1 }
    }
}
