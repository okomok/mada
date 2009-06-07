

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

/**
 * Contains concrete mixins.
 */
object Mixin {
    val bounds: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.bounds
    }

    val force: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.force
    }

    val memoize: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.memoize
    }

    val parallel: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.parallel
    }

    def parallel(grainSize: Int): Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.parallel(grainSize)
    }

    val readOnly: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.readOnly
    }
}


case class Mix[A](_1: Vector[A], _2: Mixin) extends Forwarder[A] {
    override protected val delegate = _2(_1)
    override protected def around[B](that: => Vector[B]) = that.mix(_2)

    override def mix(x: Mixin) = _1.mix(x `with` _2) // mix-mix fusion
}
