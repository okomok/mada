

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
