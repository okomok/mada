

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Filter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (x, i, j) = v.triple
        x.window(i, stl.RemoveIf(x, i, j, !p(_: A)))
    }
}
