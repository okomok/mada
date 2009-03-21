

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Sort {
    def apply[A](v: Vector[A], lt: Compare.Predicate[A]): Vector[A] = {
        Stl.sort(v, v.start, v.end, lt)
        v
    }
}

private[mada] object StableSort {
    def apply[A](v: Vector[A], lt: Compare.Predicate[A]): Vector[A] = {
        Stl.stableSort(v, v.start, v.end, lt)
        v
    }
}
