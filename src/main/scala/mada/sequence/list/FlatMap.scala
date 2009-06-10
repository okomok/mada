

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class FlatMap[Z, +A](_1: List[Z], _2: Z => List[A]) extends Forwarder[A] {
    override protected val delegate = throw new Error
}
