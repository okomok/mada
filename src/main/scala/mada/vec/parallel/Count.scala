

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Count {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Int = {
        Assert(!v.isParallel)

        v.divide(grainSize).
            parallel(1).map({ w => w.count(p) }).
                unparallel.reduce(_ + _)
    }
}
