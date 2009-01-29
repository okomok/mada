

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Sort {
    def apply[A](v: Vector[A], c: A => Ordered[A]): Vector[A] = {
        v.sortWith(Functions.less(c))
    }
}

private[mada] object SortWith {
    def apply[A](v: Vector[A], lt: (A, A) => Boolean): Vector[A] = {
        stl.Sort(v, v.start, v.end, lt)
        v
    }
}
