

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Filter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = new FilterVector(v, p)
}

class FilterVector[A](v: Vector[A], p: A => Boolean) extends VectorProxy[A] {
    override lazy val self = v.clone.mutatingFilter(p)

    override def filter(_p: A => Boolean) = v.filter({ e => p(e) && _p(e) }) // filter-filter fusion
}


object MutatingFilter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = new MutatingFilterVector(v, p)
}

class MutatingFilterVector[A](v: Vector[A], p: A => Boolean) extends VectorProxy[A] {
    override lazy val self = {
        val (x, i, j) = v.triple
        x.window(i, stl.RemoveIf(x, i, j, !p(_: A)))
    }

    override def mutatingFilter(_p: A => Boolean) = v.mutatingFilter({ e => p(e) && _p(e) }) // mutatingFilter-mutatingFilter fusion
}
