

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Find {
    def apply[A](v: Vector[A], p: A => Boolean): Option[A] = {
        var (x, i, j) = v.triple
        i = stl.FindIf(x, i, j, p)
        if (i == j) {
            None
        } else {
            Some(x(i))
        }
    }
}
