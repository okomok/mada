

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object Filter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = new FilterVector(v, p, grainSize)
}

private[mada] class FilterVector[A](v: Vector[A], p: A => Boolean, grainSize: Int) extends VectorProxy[A] {
    Assert(!v.isParallel)
    override lazy val self = v.clone.parallel(grainSize).mutatingFilter(p).readOnly
    override def filter(_p: A => Boolean) = v.parallel(grainSize).filter{ e => p(e) && _p(e) } // filter-filter fusion
}


private[mada] object MutatingFilter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)

        Vector.flatten(
            v.parallelRegions(grainSize).map{ w => w.mutatingFilter(p) }.
                unparallel.
                    toIterable // works around a compiler bug of 2.7.3.
        ).
            parallel(grainSize)
    }
}
