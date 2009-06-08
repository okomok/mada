

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class FlatMap[Z, A](_1: Vector[Z], _2: Z => Vector[A]) extends Forwarder[A] {
    override protected val delegate = _1.map(_2).flatten.toVector
}
