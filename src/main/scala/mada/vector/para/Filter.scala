

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.para


private[mada] object Filter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = {
        util.assert(!IsParallel(v))
        v.copy.parallel(grainSize).mutatingFilter(p).readOnly
    }
}

private[mada] object MutatingFilter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = {
        util.assert(!IsParallel(v))

        vector.flatten(
            v.parallelRegions(grainSize).map{ w => w.mutatingFilter(p) }.
                toIterable // works around a compiler bug of 2.7.3.
        )
    }
}
