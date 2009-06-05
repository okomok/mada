

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Slice[+A](_1: Iterative[A], _2: Int, _3: Int) extends Forwarder[A] {
    precondition.range(_2, _3, "slice")
    override protected lazy val delegate = _1.drop(_2).take(_3 - _2)
}
