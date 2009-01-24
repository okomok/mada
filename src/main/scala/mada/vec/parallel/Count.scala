

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Count {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Int = {
        Assert(!v.isParallel)

        val n = new java.util.concurrent.atomic.AtomicInteger(0)
        v.parallel(grainSize).pareach({ e => if (p(e)) n.incrementAndGet })
        n.get
    }
}
