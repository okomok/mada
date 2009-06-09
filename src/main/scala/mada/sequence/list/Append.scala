

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Append[+A](_1: List[A], _2: List[A]) extends Forwarder[A] {
    override protected val delegate = if (_1.isNil) _2 else cons(_1.head, _1.tail ++ _2)
}
