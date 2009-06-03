

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


package object mixin {

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
