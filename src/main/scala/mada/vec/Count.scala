

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Count {
    def apply[A](v: Vector[A], p: A => Boolean): Int = {
        val (x, first, last) = v.triple
        stl.CountIf(x, first, last, p)
    }
}
