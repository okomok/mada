

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object CompareBy {
    def apply[A](_lt: Compare.Type[A]): Compare.Type[Vector[A]] = {
        val ord = new Ordering[Vector[A]] {
            override def compare(v: Vector[A], w: Vector[A]) = Stl.lexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, _lt)
        }
        Compare.fromOrdering(ord)
    }
}
