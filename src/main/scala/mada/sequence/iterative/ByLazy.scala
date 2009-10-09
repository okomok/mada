

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class ByLazy[+A](_1: util.ByLazy[Iterative[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
