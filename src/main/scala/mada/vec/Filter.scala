

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Filter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = new FilterVector(v, p)
}

private[mada] class FilterVector[A](v: Vector[A], p: A => Boolean) extends Adapter.Proxy[A] with NotWritable[A] {
    override lazy val self = v.clone.mutatingFilter(p)
    override def filter(_p: A => Boolean) = v.filter({ e => p(e) && _p(e) }) // filter-filter fusion
}


private[mada] object MutatingFilter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = new MutatingFilterVector(v, p)
}

private[mada] class MutatingFilterVector[A](v: Vector[A], p: A => Boolean) extends Adapter.Proxy[A] {
    override lazy val self = {
        v(v.start, stl.RemoveIf(v, v.start, v.end, Functions.not(p)))
    }
    override def mutatingFilter(_p: A => Boolean) = v.mutatingFilter({ e => p(e) && _p(e) }) // mutatingFilter-mutatingFilter fusion
}
