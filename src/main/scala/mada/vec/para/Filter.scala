

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object Filter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = new FilterVector(v, p, grainSize)
}

private[mada] class FilterVector[A](v: Vector[A], p: A => Boolean, grainSize: Int) extends VectorProxy[A] with NotWritable[A] {
    Assert(!v.isParallel)
    override lazy val self = v.clone.parallel(grainSize).mutatingFilter(p)
    override def filter(_p: A => Boolean) = v.parallel(grainSize).filter{ e => p(e) && _p(e) } // filter-filter fusion
}


private[mada] object MutatingFilter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)

        Vector.flatten(
            v.divide(grainSize).parallel(1).map{ w => w.mutatingFilter(p) }.
                unparallel.toIterator
        ).
            parallel(grainSize)
    }
}
