

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Sort {
    def apply[A](v: Vector[A])(implicit c: A => Ordered[A]): Vector[A] = {
        v.sort({ (x: A, y: A) => c(x) < y })
    }

    def apply[A](v: Vector[A], lt: (A, A) => Boolean): Vector[A] = {
        val (first, last) = v.toPair
        stl.Sort(v, first, last, lt)
        v
    }
}
