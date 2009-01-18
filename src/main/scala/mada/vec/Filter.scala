

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Filter {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (first, last) = v.pair
        v.window(first, stl.RemoveIf(v, first, last, !p(_: A)))
    }
}
