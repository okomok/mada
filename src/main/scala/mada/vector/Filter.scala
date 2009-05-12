

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Filter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = new FilterVector(v, p)
}

private[mada] class FilterVector[A](v: Vector[A], p: A => Boolean) extends VectorProxy[A] {
    override lazy val self = v.clone.mutatingFilter(p).readOnly
    override def filter(_p: A => Boolean) = v.filter{ e => p(e) && _p(e) } // filter-filter fusion
}


private[mada] object MutatingFilter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = v(v.start, stl.RemoveIf(v, v.start, v.end, function.not(p)))
}
