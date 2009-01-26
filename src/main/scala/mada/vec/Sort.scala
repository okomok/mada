

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Sort {
    def apply[A](v: Vector[A], c: A => Ordered[A]): Vector[A] = {
        v.sortWith(stl.Less(c))
    }
}

object SortWith {
    def apply[A](v: Vector[A], lt: (A, A) => Boolean): Vector[A] = {
        val (x, i, j) = v.triple
        stl.Sort(x, i, j, lt)
        v
    }
}
