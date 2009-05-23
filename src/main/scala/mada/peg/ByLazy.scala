

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class ByLazy[A](p: => Peg[A]) extends Forwarder[A] {
    val _1 = function.ofLazy(p)

    override protected def delegate = _1()
}
