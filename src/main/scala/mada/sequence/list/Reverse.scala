

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Reverse[+A](_1: List[A]) extends Forwarder[A] {
    override protected val delegate: List[A] = foldLeft[List[A]](nil)((xs, x) => cons(x, xs))
}
