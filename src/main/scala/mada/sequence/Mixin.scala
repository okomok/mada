

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/**
 * Applies <code>Mixin</code> to result of sequence-to-sequence methods.
 */
case class Mix[+A](_1: Sequence[A], _2: Mixin) extends Forwarder[A] {
    override protected lazy val delegate = _2(_1)
    override protected def afterForward[B](that: Sequence[B]) = that.mix(_2)

    override def mix(x: Mixin) = _1.mix(x `with` _2) // mix-mix fusion
}


/**
 * Mixin
 */
trait Mixin { self =>
    def apply[B](seq: Sequence[B]): Sequence[B]

    /**
     * Mixin composition
     */
    final def `with`(that: Mixin): Mixin = new Mixin {
        override def apply[B](seq: Sequence[B]) = that(self(seq))
    }
}
