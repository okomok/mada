

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


/**
 * Mixin
 */
trait Mixin { self =>
    def apply[B](v: Vector[B]): Vector[B]

    /**
     * Mixin composition
     */
    final def `with`(that: Mixin): Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = that(self(v))
    }
}


case class Mix[A](_1: Vector[A], _2: Mixin) extends Forwarder[A] {
    override protected val delegate = _2(_1)
    override protected def around[B](that: => Vector[B]) = that.mix(_2)

    override def mix(x: Mixin) = _1.mix(x `with` _2) // mix-mix fusion
}
