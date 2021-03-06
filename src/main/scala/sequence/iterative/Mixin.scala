

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


/**
 * Mixin
 */
trait Mixin { self =>
    def apply[B](it: Iterative[B]): Iterative[B]

    /**
     * Mixin composition
     */
    final def `with`(that: Mixin): Mixin = new Mixin {
        override def apply[B](it: Iterative[B]) = that(self(it))
    }
}

/**
 * Contains concrete mixins.
 */
object Mixin {
    val force = new Mixin {
        override def apply[B](it: Iterative[B]) = it.force
    }

    val seal = new Mixin {
        override def apply[B](it: Iterative[B]) = it.seal
    }
}


@annotation.visibleForTesting
case class Mix[+A](_1: Iterative[A], _2: Mixin) extends Forwarder[A] {
    override protected val delegate = _2(_1) // can't be lazy for `Mixin.force`.
    override protected def around[B](that: => Iterative[B]) = that.mix(_2)

    override def mix(x: Mixin) = _1.mix(x `with` _2) // mix.mix fusion
}
