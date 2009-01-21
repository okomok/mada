

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Filter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (x, first, last) = v.triple
        x.window(first, stl.RemoveIf(x, first, last, !p(_: A)))
    }
}
