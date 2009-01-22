

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


// This is mutating algorithm.

object Filter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = new FilterVector(v, p, grainSize)
}

class FilterVector[A](v: Vector[A], p: A => Boolean, grainSize: Int) extends VectorProxy[A] {
    override lazy val self = {
        Vector.flatten(
            v.divide(grainSize).parallel(1).map({ w => w.filter(p) }).unparallel
        )
    }

    override def filter(_p: A => Boolean) = v.parallel(grainSize).filter({ e => p(e) && _p(e) }) // filter-filter fusion
}
