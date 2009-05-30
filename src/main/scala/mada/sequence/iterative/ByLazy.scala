

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


class ByLazy[+A](seq: => Iterative[A]) extends Forwarder[A] {
    val _1 = function.ofLazy(seq)

    override protected def delegate = _1()
}
