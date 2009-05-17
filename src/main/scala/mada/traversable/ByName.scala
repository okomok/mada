

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class ByName[+A](tr: => Traversable[A]) extends Forwarder[A] {
    val _1 = function.ofName(tr)
    override protected def delegate = _1()
}
