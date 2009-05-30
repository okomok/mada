

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


/**
 * Mixin
 */
trait Mixin { self =>
    def apply[B](seq: Iterative[B]): Iterative[B]

    /**
     * Mixin composition
     */
    final def `with`(that: Mixin): Mixin = new Mixin {
        override def apply[B](seq: Iterative[B]) = that(self(seq))
    }
}
