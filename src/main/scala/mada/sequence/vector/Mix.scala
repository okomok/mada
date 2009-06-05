

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Mix[A](_1: Vector[A], _2: Mixin) extends Forwarder[A] {
    override protected val delegate = _2(_1)
    override protected def afterForward[B](that: Vector[B]) = that.mix(_2)

    override def mix(x: Mixin) = _1.mix(x `with` _2) // mix-mix fusion
}
