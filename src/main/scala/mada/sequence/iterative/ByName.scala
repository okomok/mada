

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class ByName[+A](_1: function.OfName[Iterative[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}