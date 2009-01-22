

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Filter {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Vector[A] = new FilterVector(v, p)
}

class FilterVector[A](v: Vector[A], p: A => Boolean) extends VectorProxy[A] {
    override val self = Vector.flatten(v.divide(grainSize).parallel(1).map({ w => w.filter(p) }))

    override def filter(_p: A => Boolean) = v.parallel(grainSize).filter({ e => p(e) && _p(e) }) // filter-filter fusion
}
