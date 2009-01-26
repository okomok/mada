

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Count {
    def apply[A](v: Vector[A], p: A => Boolean): Int = {
        val (x, i, j) = v.triple
        stl.CountIf(x, i, j, p)
    }
}
