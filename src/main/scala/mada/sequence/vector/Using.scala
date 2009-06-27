

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Using[A, X](_1: Vector[A], _2: X, _3: Auto[X]) extends Forwarder[A] with auto.Interface {
    override protected val delegate = _1

    override def autoBegin = _3.begin(_2)
    override def autoEnd = _3.end(_2)
}
