

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Cycle[+A](_1: List[A]) extends Forwarder[A] {
    Precondition.notEmpty(_1, "cycle")
    override protected lazy val delegate: List[A] = _1 ++ byName(delegate)

    override def isNil = false
}
