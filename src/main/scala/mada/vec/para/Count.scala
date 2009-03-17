

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object Count {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Int = {
        Assert(!IsParallel(v))
        v.parallelRegions(grainSize).map{ w => w.count(p) }.
            reduce(_ + _)
    }
}
