

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Flatten[+A](_1: List[List[A]]) extends Forwarder[A] {
    override protected val delegate: List[A] = _1.foldRight[List[A]](nil)(_() ++ _())
}
