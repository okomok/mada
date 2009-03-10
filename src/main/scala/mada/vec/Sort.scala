

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object SortBy {
    def apply[A](v: Vector[A], lt: Compare.Type[A]): Vector[A] = {
        stl.Sort(v, v.start, v.end, lt)
        v
    }
}
