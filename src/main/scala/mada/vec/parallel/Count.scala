

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Count {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Long): Long = {
        if (v.isEmpty) {
            0
        } else {
            val n = new java.util.concurrent.atomic.AtomicLong(0)
            v.parallel(grainSize).foreach({ e => if (p(e)) n.incrementAndGet })
            n.get
        }
    }
}
